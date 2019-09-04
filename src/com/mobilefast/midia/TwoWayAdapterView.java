package com.mobilefast.midia;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.Handler;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.Adapter;


   @SuppressLint("WrongCall")
public abstract class TwoWayAdapterView<T extends Adapter> extends
            ViewGroup {

        /**
         * The item view type returned by {@link Adapter#getItemViewType(int)} when
         * the adapter does not want the item's view recycled.
         */
        public static final int ITEM_VIEW_TYPE_IGNORE = -1;

        /**
         * The item view type returned by {@link Adapter#getItemViewType(int)} when
         * the item is a header or footer.
         */
        public static final int ITEM_VIEW_TYPE_HEADER_OR_FOOTER = -2;

        protected Context mContext = null;

        /**
         * Whether the view displays its items vertically or horizontally
         */
        protected boolean mIsVertical = true;

        /**
         * The position of the first child displayed
         */
        @ViewDebug.ExportedProperty
        int mFirstPosition = 0;
            int mSpecificTop;

        int mSyncPosition;
        long mSyncRowId = INVALID_ROW_ID;

       long mSyncSize;

        boolean mNeedSync = false;

        int mSyncMode;


        private int mLayoutHeight;

        private int mLayoutWidth;

        static final int SYNC_SELECTED_POSITION = 0;

        static final int SYNC_FIRST_POSITION = 1;

        static final int SYNC_MAX_DURATION_MILLIS = 100;

        boolean mInLayout = false;

        OnItemSelectedListener mOnItemSelectedListener;

        OnItemClickListener mOnItemClickListener;

        OnItemLongClickListener mOnItemLongClickListener;

        boolean mDataChanged;

        @ViewDebug.ExportedProperty
        int mNextSelectedPosition = INVALID_POSITION;

        long mNextSelectedRowId = INVALID_ROW_ID;


        @ViewDebug.ExportedProperty
        int mSelectedPosition = INVALID_POSITION;


        long mSelectedRowId = INVALID_ROW_ID;

        private View mEmptyView;


        @ViewDebug.ExportedProperty
        int mItemCount;

        /**
         * The number of items in the adapter before a data changed event occured.
         */
        int mOldItemCount;

        /**
         * Represents an invalid position. All valid positions are in the range 0 to 1 less than the
         * number of items in the current adapter.
         */
        public static final int INVALID_POSITION = -1;

        /**
         * Represents an empty or invalid row id
         */
        public static final long INVALID_ROW_ID = Long.MIN_VALUE;

        /**
         * The last selected position we used when notifying
         */
        int mOldSelectedPosition = INVALID_POSITION;

        /**
         * The id of the last selected position we used when notifying
         */
        long mOldSelectedRowId = INVALID_ROW_ID;

        /**
0230         * Indicates what focusable state is requested when calling setFocusable().
0231         * In addition to this, this view has other criteria for actually
0232         * determining the focusable state (such as whether its empty or the text
0233         * filter is shown).
0234         *
0235         * @see #setFocusable(boolean)
0236         * @see #checkFocus()
0237         */
        private boolean mDesiredFocusableState;
        private boolean mDesiredFocusableInTouchModeState;

        private SelectionNotifier mSelectionNotifier;
        /**
0243         * When set to true, calls to requestLayout() will not propagate up the parent hierarchy.
0244         * This is used to layout the children during a layout pass.
0245         */
        boolean mBlockLayoutRequests = false;

        public TwoWayAdapterView(Context context) {
            super (context);
            mContext = context;
        }

        public TwoWayAdapterView(Context context, AttributeSet attrs) {
            super (context, attrs);
            mContext = context;
        }

        public TwoWayAdapterView(Context context, AttributeSet attrs,
                int defStyle) {
            super (context, attrs, defStyle);
            mContext = context;
        }

        /**
0265         * Interface definition for a callback to be invoked when an item in this
0266         * AdapterView has been clicked.
0267         */
        public interface OnItemClickListener {

            /**
0271             * Callback method to be invoked when an item in this AdapterView has
0272             * been clicked.
0273             * <p>
0274             * Implementers can call getItemAtPosition(position) if they need
0275             * to access the data associated with the selected item.
0276             *
0277             * @param parent The AdapterView where the click happened.
0278             * @param view The view within the AdapterView that was clicked (this
0279             *            will be a view provided by the adapter)
0280             * @param position The position of the view in the adapter.
0281             * @param id The row id of the item that was clicked.
0282             */
            void onItemClick(TwoWayAdapterView<?> parent, View view,
                    int position, long id);
        }

        /**
0288         * Register a callback to be invoked when an item in this AdapterView has
0289         * been clicked.
0290         *
0291         * @param listener The callback that will be invoked.
0292         */
        public void setOnItemClickListener(OnItemClickListener listener) {
            mOnItemClickListener = listener;
        }

        /**
0298         * @return The callback to be invoked with an item in this AdapterView has
0299         *         been clicked, or null id no callback has been set.
0300         */
        public final OnItemClickListener getOnItemClickListener() {
            return mOnItemClickListener;
        }

        /**
0306         * Call the OnItemClickListener, if it is defined.
0307         *
0308         * @param view The view within the AdapterView that was clicked.
0309         * @param position The position of the view in the adapter.
0310         * @param id The row id of the item that was clicked.
0311         * @return True if there was an assigned OnItemClickListener that was
0312         *         called, false otherwise is returned.
0313         */
        public boolean performItemClick(View view, int position, long id) {
            if (mOnItemClickListener != null) {
                playSoundEffect(SoundEffectConstants.CLICK);
                mOnItemClickListener.onItemClick(this , view, position, id);
                return true;
            }

            return false;
        }

        /**
0325         * Interface definition for a callback to be invoked when an item in this
0326         * view has been clicked and held.
0327         */
        public interface OnItemLongClickListener {
            /**
0330             * Callback method to be invoked when an item in this view has been
0331             * clicked and held.
0332             *
0333             * Implementers can call getItemAtPosition(position) if they need to access
0334             * the data associated with the selected item.
0335             *
0336             * @param parent The AbsListView where the click happened
0337             * @param view The view within the AbsListView that was clicked
0338             * @param position The position of the view in the list
0339             * @param id The row id of the item that was clicked
0340             *
0341             * @return true if the callback consumed the long click, false otherwise
0342             */
            boolean onItemLongClick(TwoWayAdapterView<?> parent, View view,
                    int position, long id);
        }

        /**
0348         * Register a callback to be invoked when an item in this AdapterView has
0349         * been clicked and held
0350         *
0351         * @param listener The callback that will run
0352         */
        public void setOnItemLongClickListener(OnItemLongClickListener listener) {
            if (!isLongClickable()) {
                setLongClickable(true);
            }
            mOnItemLongClickListener = listener;
        }

        /**
0361         * @return The callback to be invoked with an item in this AdapterView has
0362         *         been clicked and held, or null id no callback as been set.
0363         */
        public final OnItemLongClickListener getOnItemLongClickListener() {
            return mOnItemLongClickListener;
        }

        /**
0369         * Interface definition for a callback to be invoked when
0370         * an item in this view has been selected.
0371         */
        public interface OnItemSelectedListener {
            /**
0374             * Callback method to be invoked when an item in this view has been
0375             * selected.
0376             *
0377             * Impelmenters can call getItemAtPosition(position) if they need to access the
0378             * data associated with the selected item.
0379             *
0380             * @param parent The AdapterView where the selection happened
0381             * @param view The view within the AdapterView that was clicked
0382             * @param position The position of the view in the adapter
0383             * @param id The row id of the item that is selected
0384             */
            void onItemSelected(TwoWayAdapterView<?> parent, View view,
                    int position, long id);

            /**
0389             * Callback method to be invoked when the selection disappears from this
0390             * view. The selection can disappear for instance when touch is activated
0391             * or when the adapter becomes empty.
0392             *
0393             * @param parent The AdapterView that now contains no selected item.
0394             */
            void onNothingSelected(TwoWayAdapterView<?> parent);
        }

        /**
0399         * Register a callback to be invoked when an item in this AdapterView has
0400         * been selected.
0401         *
0402         * @param listener The callback that will run
0403         */
        public void setOnItemSelectedListener(OnItemSelectedListener listener) {
            mOnItemSelectedListener = listener;
        }

        public final OnItemSelectedListener getOnItemSelectedListener() {
            return mOnItemSelectedListener;
        }

        /**
0413         * Extra menu information provided to the
0414         * {@link android.view.View.OnCreateContextMenuListener#onCreateContextMenu(ContextMenu, View, ContextMenuInfo) }
0415         * callback when a context menu is brought up for this AdapterView.
0416         *
0417         */
        public static class AdapterContextMenuInfo implements 
                ContextMenu.ContextMenuInfo {

            public AdapterContextMenuInfo(View targetView, int position, long id) {
                this .targetView = targetView;
                this .position = position;
                this .id = id;
            }

            /**
0428             * The child view for which the context menu is being displayed. This
0429             * will be one of the children of this AdapterView.
0430             */
            public View targetView;

            /**
0434             * The position in the adapter for which the context menu is being
0435             * displayed.
0436             */
            public int position;

            /**
0440             * The row id of the item for which the context menu is being displayed.
0441             */
            public long id;
        }

        /**
0446         * Returns the adapter currently associated with this widget.
0447         *
0448         * @return The adapter used to provide this view's content.
0449         */
        public abstract T getAdapter();

        /**
0453         * Sets the adapter that provides the data and the views to represent the data
0454         * in this widget.
0455         *
0456         * @param adapter The adapter to use to create this view's content.
0457         */
        public abstract void setAdapter(T adapter);

        /**
0461         * This method is not supported and throws an UnsupportedOperationException when called.
0462         *
0463         * @param child Ignored.
0464         *
0465         * @throws UnsupportedOperationException Every time this method is invoked.
0466         */
        @Override
        public void addView(View child) {
            throw new UnsupportedOperationException(
                    "addView(View) is not supported in AdapterView");
        }

        /**
0474         * This method is not supported and throws an UnsupportedOperationException when called.
0475         *
0476         * @param child Ignored.
0477         * @param index Ignored.
0478         *
0479         * @throws UnsupportedOperationException Every time this method is invoked.
0480         */
        @Override
        public void addView(View child, int index) {
            throw new UnsupportedOperationException(
                    "addView(View, int) is not supported in AdapterView");
        }

        /**
0488         * This method is not supported and throws an UnsupportedOperationException when called.
0489         *
0490         * @param child Ignored.
0491         * @param params Ignored.
0492         *
0493         * @throws UnsupportedOperationException Every time this method is invoked.
0494         */
        @Override
        public void addView(View child, LayoutParams params) {
            throw new UnsupportedOperationException(
                    "addView(View, LayoutParams) "
                            + "is not supported in AdapterView");
        }

        /**
0503         * This method is not supported and throws an UnsupportedOperationException when called.
0504         *
0505         * @param child Ignored.
0506         * @param index Ignored.
0507         * @param params Ignored.
0508         *
0509         * @throws UnsupportedOperationException Every time this method is invoked.
0510         */
        @Override
        public void addView(View child, int index, LayoutParams params) {
            throw new UnsupportedOperationException(
                    "addView(View, int, LayoutParams) "
                            + "is not supported in AdapterView");
        }

        /**
0519         * This method is not supported and throws an UnsupportedOperationException when called.
0520         *
0521         * @param child Ignored.
0522         *
0523         * @throws UnsupportedOperationException Every time this method is invoked.
0524         */
        @Override
        public void removeView(View child) {
            throw new UnsupportedOperationException(
                    "removeView(View) is not supported in AdapterView");
        }

        /**
0532         * This method is not supported and throws an UnsupportedOperationException when called.
0533         *
0534         * @param index Ignored.
0535         *
0536         * @throws UnsupportedOperationException Every time this method is invoked.
0537         */
        @Override
        public void removeViewAt(int index) {
            throw new UnsupportedOperationException(
                    "removeViewAt(int) is not supported in AdapterView");
        }

        /**
0545         * This method is not supported and throws an UnsupportedOperationException when called.
0546         *
0547         * @throws UnsupportedOperationException Every time this method is invoked.
0548         */
        @Override
        public void removeAllViews() {
            throw new UnsupportedOperationException(
                    "removeAllViews() is not supported in AdapterView");
        }

        @Override
        protected void onLayout(boolean changed, int left, int top, int right,
                int bottom) {
            mLayoutHeight = getHeight();
            mLayoutWidth = getWidth();
        }

        /**
0563         * Return the position of the currently selected item within the adapter's data set
0564         *
0565         * @return int Position (starting at 0), or {@link #INVALID_POSITION} if there is nothing selected.
0566         */
        @ViewDebug.CapturedViewProperty
        public int getSelectedItemPosition() {
            return mNextSelectedPosition;
        }

        /**
0573         * @return The id corresponding to the currently selected item, or {@link #INVALID_ROW_ID}
0574         * if nothing is selected.
0575         */
        @ViewDebug.CapturedViewProperty
        public long getSelectedItemId() {
            return mNextSelectedRowId;
        }

        /**
0582         * @return The view corresponding to the currently selected item, or null
0583         * if nothing is selected
0584         */
        public abstract View getSelectedView();

        /**
0588         * @return The data corresponding to the currently selected item, or
0589         * null if there is nothing selected.
0590         */
        public Object getSelectedItem() {
            T adapter = getAdapter();
            int selection = getSelectedItemPosition();
            if (adapter != null && adapter.getCount() > 0 && selection >= 0) {
                return adapter.getItem(selection);
            } else {
                return null;
            }
        }

        /**
         * @return The number of items owned by the Adapter associated with this
         *         AdapterView. (This is the number of data items, which may be
         *         larger than the number of visible view.)
         */
        @ViewDebug.CapturedViewProperty
        public int getCount() {
            return mItemCount;
        }

        /**
0612         * Get the position within the adapter's data set for the view, where view is a an adapter item
0613         * or a descendant of an adapter item.
0614         *
0615         * @param view an adapter item, or a descendant of an adapter item. This must be visible in this
0616         *        AdapterView at the time of the call.
0617         * @return the position within the adapter's data set of the view, or {@link #INVALID_POSITION}
0618         *         if the view does not correspond to a list item (or it is not currently visible).
0619         */
        public int getPositionForView(View view) {
            View listItem = view;
            try {
                View v;
                while (!(v = (View) listItem.getParent()).equals(this )) {
                    listItem = v;
                }
            } catch (ClassCastException e) {
                // We made it up to the window without find this list view
                return INVALID_POSITION;
            }

            // Search the children for the list item
            final int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (getChildAt(i).equals(listItem)) {
                    return mFirstPosition + i;
                }
            }

            // Child not found!
            return INVALID_POSITION;
        }

        /**
0645         * Returns the position within the adapter's data set for the first item
0646         * displayed on screen.
0647         *
0648         * @return The position within the adapter's data set
0649         */
        public int getFirstVisiblePosition() {
            return mFirstPosition;
        }

        /**
0655         * Returns the position within the adapter's data set for the last item
0656         * displayed on screen.
0657         *
0658         * @return The position within the adapter's data set
0659         */
        public int getLastVisiblePosition() {
            return mFirstPosition + getChildCount() - 1;
        }

        /**
0665         * Sets the currently selected item. To support accessibility subclasses that
0666         * override this method must invoke the overriden super method first.
0667         *
0668         * @param position Index (starting at 0) of the data item to be selected.
0669         */
        public abstract void setSelection(int position);

        /**
0673         * Sets the view to show if the adapter is empty
0674         */
        public void setEmptyView(View emptyView) {
            mEmptyView = emptyView;

            final T adapter = getAdapter();
            final boolean empty = ((adapter == null) || adapter.isEmpty());
            updateEmptyStatus(empty);
        }

        /**
0684         * When the current adapter is empty, the AdapterView can display a special view
0685         * call the empty view. The empty view is used to provide feedback to the user
0686         * that no data is available in this AdapterView.
0687         *
0688         * @return The view to show if the adapter is empty.
0689         */
        public View getEmptyView() {
            return mEmptyView;
        }

        /**
0695         * Indicates whether this view is in filter mode. Filter mode can for instance
0696         * be enabled by a user when typing on the keyboard.
0697         *
0698         * @return True if the view is in filter mode, false otherwise.
0699         */
        boolean isInFilterMode() {
            return false;
        }

        @Override
        public void setFocusable(boolean focusable) {
            final T adapter = getAdapter();
            final boolean empty = adapter == null || adapter.getCount() == 0;

            mDesiredFocusableState = focusable;
            if (!focusable) {
                mDesiredFocusableInTouchModeState = false;
            }

            super .setFocusable(focusable && (!empty || isInFilterMode()));
        }

        @Override
        public void setFocusableInTouchMode(boolean focusable) {
            final T adapter = getAdapter();
            final boolean empty = adapter == null || adapter.getCount() == 0;

            mDesiredFocusableInTouchModeState = focusable;
            if (focusable) {
                mDesiredFocusableState = true;
            }

            super .setFocusableInTouchMode(focusable
                    && (!empty || isInFilterMode()));
        }

        void checkFocus() {
            final T adapter = getAdapter();
            final boolean empty = adapter == null || adapter.getCount() == 0;
            final boolean focusable = !empty || isInFilterMode();
            // The order in which we set focusable in touch mode/focusable may matter
            // for the client, see View.setFocusableInTouchMode() comments for more
            // details
            super .setFocusableInTouchMode(focusable
                    && mDesiredFocusableInTouchModeState);
            super .setFocusable(focusable && mDesiredFocusableState);
            if (mEmptyView != null) {
                updateEmptyStatus((adapter == null) || adapter.isEmpty());
            }
        }

        /**
0747         * Update the status of the list based on the empty parameter.  If empty is true and
0748         * we have an empty view, display it.  In all the other cases, make sure that the listview
0749         * is VISIBLE and that the empty view is GONE (if it's not null).
0750         */
        private void updateEmptyStatus(boolean empty) {
            if (isInFilterMode()) {
                empty = false;
            }

            if (empty) {
                if (mEmptyView != null) {
                    mEmptyView.setVisibility(View.VISIBLE);
                    setVisibility(View.GONE);
                } else {
                    // If the caller just removed our empty view, make sure the list view is visible
                    setVisibility(View.VISIBLE);
                }

                // We are now GONE, so pending layouts will not be dispatched.
                // Force one here to make sure that the state of the list matches
                // the state of the adapter.
                if (mDataChanged) {
                    this .onLayout(false, getLeft(), getTop(), getRight(),
                            getBottom());
                }
            } else {
                if (mEmptyView != null)
                    mEmptyView.setVisibility(View.GONE);
                setVisibility(View.VISIBLE);
            }
        }

        /**
0780         * Gets the data associated with the specified position in the list.
0781         *
0782         * @param position Which data to get
0783         * @return The data associated with the specified position in the list
0784         */
        public Object getItemAtPosition(int position) {
            T adapter = getAdapter();
            return (adapter == null || position < 0) ? null : adapter
                    .getItem(position);
        }

        public long getItemIdAtPosition(int position) {
            T adapter = getAdapter();
            return (adapter == null || position < 0) ? INVALID_ROW_ID : adapter
                    .getItemId(position);
       }

        @Override
        public void setOnClickListener(OnClickListener l) {
            throw new RuntimeException(
                    "Don't call setOnClickListener for an AdapterView. "
                            + "You probably want setOnItemClickListener instead");
        }

        /**
0805         * Override to prevent freezing of any views created by the adapter.
0806         */
        @Override
        protected void dispatchSaveInstanceState(
                SparseArray<Parcelable> container) {
            dispatchFreezeSelfOnly(container);
        }

        /**
0814         * Override to prevent thawing of any views created by the adapter.
0815         */
        @Override
        protected void dispatchRestoreInstanceState(
                SparseArray<Parcelable> container) {
            dispatchThawSelfOnly(container);
        }

        class AdapterDataSetObserver extends DataSetObserver {

            private Parcelable mInstanceState = null;

            @Override
            public void onChanged() {
                mDataChanged = true;
                mOldItemCount = mItemCount;
                mItemCount = getAdapter().getCount();

                // Detect the case where a cursor that was previously invalidated has
                // been repopulated with new data.
                if (TwoWayAdapterView.this .getAdapter().hasStableIds()
                        && mInstanceState != null && mOldItemCount == 0
                        && mItemCount > 0) {
                    TwoWayAdapterView.this 
                            .onRestoreInstanceState(mInstanceState);
                    mInstanceState = null;
                } else {
                    rememberSyncState();
                }
                checkFocus();
                requestLayout();
            }

            @Override
            public void onInvalidated() {
                mDataChanged = true;

                if (TwoWayAdapterView.this .getAdapter().hasStableIds()) {
                    // Remember the current state for the case where our hosting activity is being
                    // stopped and later restarted
                    mInstanceState = TwoWayAdapterView.this 
                            .onSaveInstanceState();
                }

                // Data is invalid so we should reset our state
                mOldItemCount = mItemCount;
                mItemCount = 0;
                mSelectedPosition = INVALID_POSITION;
                mSelectedRowId = INVALID_ROW_ID;
                mNextSelectedPosition = INVALID_POSITION;
                mNextSelectedRowId = INVALID_ROW_ID;
                mNeedSync = false;
                checkSelectionChanged();

                checkFocus();
                requestLayout();
            }

            public void clearSavedState() {
                mInstanceState = null;
            }
        }

        private class SelectionNotifier extends Handler implements  Runnable {
            public void run() {
                if (mDataChanged) {
                    // Data has changed between when this SelectionNotifier
                    // was posted and now. We need to wait until the AdapterView
                    // has been synched to the new data.
                    post(this );
                } else {
                    fireOnSelected();
                }
            }
        }

        void selectionChanged() {
            if (mOnItemSelectedListener != null) {
                if (mInLayout || mBlockLayoutRequests) {
                    // If we are in a layout traversal, defer notification
                    // by posting. This ensures that the view tree is
                    // in a consistent state and is able to accomodate
                    // new layout or invalidate requests.
                    if (mSelectionNotifier == null) {
                        mSelectionNotifier = new SelectionNotifier();
                    }
                    mSelectionNotifier.post(mSelectionNotifier);
                } else {
                    fireOnSelected();
                }
            }

           // we fire selection events here not in View
            /* taken out for backward compatibility
0908            if (mSelectedPosition != ListView.INVALID_POSITION && isShown() && !isInTouchMode()) {
0909            	sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_SELECTED);
0910            }*/
        }

        private void fireOnSelected() {
            if (mOnItemSelectedListener == null)
                return;

            int selection = this .getSelectedItemPosition();
            if (selection >= 0) {
                View v = getSelectedView();
                mOnItemSelectedListener.onItemSelected(this , v, selection,
                        getAdapter().getItemId(selection));
            } else {
                mOnItemSelectedListener.onNothingSelected(this );
            }
        }

        /* taken out for backward compatibility
0928        @Override
0929        public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
0930        	boolean populated = false;
0931        	// This is an exceptional case which occurs when a window gets the
0932        	// focus and sends a focus event via its focused child to announce
0933        	// current focus/selection. AdapterView fires selection but not focus
0934        	// events so we change the event type here.
0935        	if (event.getEventType() == AccessibilityEvent.TYPE_VIEW_FOCUSED) {
0936        		event.setEventType(AccessibilityEvent.TYPE_VIEW_SELECTED);
0937        	}
0938
0939        	// we send selection events only from AdapterView to avoid
0940        	// generation of such event for each child
0941        	View selectedView = getSelectedView();
0942        	if (selectedView != null) {
0943        		populated = selectedView.dispatchPopulateAccessibilityEvent(event);
0944        	}
0945
0946        	if (!populated) {
0947        		if (selectedView != null) {
0948        			event.setEnabled(selectedView.isEnabled());
0949        		}
0950        		event.setItemCount(getCount());
0951        		event.setCurrentItemIndex(getSelectedItemPosition());
0952        	}
0953
0954        	return populated;
0955        }*/

        @Override
        protected boolean canAnimate() {
            return super .canAnimate() && mItemCount > 0;
        }

        void handleDataChanged() {
            final int count = mItemCount;
            boolean found = false;

            if (count > 0) {

                int newPos;

                // Find the row we are supposed to sync to
                if (mNeedSync) {
                    // Update this first, since setNextSelectedPositionInt inspects
                    // it
                    mNeedSync = false;

                    // See if we can find a position in the new data with the same
                    // id as the old selection
                    newPos = findSyncPosition();
                    if (newPos >= 0) {
                        // Verify that new selection is selectable
                        int selectablePos = lookForSelectablePosition(newPos,
                                true);
                        if (selectablePos == newPos) {
                            // Same row id is selected
                            setNextSelectedPositionInt(newPos);
                            found = true;
                        }
                    }
                }
                if (!found) {
                    // Try to use the same position if we can't find matching data
                    newPos = getSelectedItemPosition();

                    // Pin position to the available range
                    if (newPos >= count) {
                        newPos = count - 1;
                    }
                    if (newPos < 0) {
                        newPos = 0;
                    }

                    // Make sure we select something selectable -- first look down
                    int selectablePos = lookForSelectablePosition(newPos, true);
                    if (selectablePos < 0) {
                        // Looking down didn't work -- try looking up
                        selectablePos = lookForSelectablePosition(newPos, false);
                    }
                    if (selectablePos >= 0) {
                        setNextSelectedPositionInt(selectablePos);
                        checkSelectionChanged();
                        found = true;
                    }
                }
            }
            if (!found) {
                // Nothing is selected
                mSelectedPosition = INVALID_POSITION;
                mSelectedRowId = INVALID_ROW_ID;
                mNextSelectedPosition = INVALID_POSITION;
                mNextSelectedRowId = INVALID_ROW_ID;
                mNeedSync = false;
                checkSelectionChanged();
            }
        }

        void checkSelectionChanged() {
            if ((mSelectedPosition != mOldSelectedPosition)
                    || (mSelectedRowId != mOldSelectedRowId)) {
                selectionChanged();
                mOldSelectedPosition = mSelectedPosition;
                mOldSelectedRowId = mSelectedRowId;
            }
        }

        /**
1036         * Searches the adapter for a position matching mSyncRowId. The search starts at mSyncPosition
1037         * and then alternates between moving up and moving down until 1) we find the right position, or
1038         * 2) we run out of time, or 3) we have looked at every position
1039         *
1040         * @return Position of the row that matches mSyncRowId, or {@link #INVALID_POSITION} if it can't
1041         *         be found
1042         */
        int findSyncPosition() {
            int count = mItemCount;

            if (count == 0) {
                return INVALID_POSITION;
            }

            long idToMatch = mSyncRowId;
            int seed = mSyncPosition;

            // If there isn't a selection don't hunt for it
            if (idToMatch == INVALID_ROW_ID) {
                return INVALID_POSITION;
            }

            // Pin seed to reasonable values
            seed = Math.max(0, seed);
            seed = Math.min(count - 1, seed);

            long endTime = SystemClock.uptimeMillis()
                    + SYNC_MAX_DURATION_MILLIS;

            long rowId;

            // first position scanned so far
            int first = seed;

            // last position scanned so far
            int last = seed;

            // True if we should move down on the next iteration
            boolean next = false;

            // True when we have looked at the first item in the data
            boolean hitFirst;

            // True when we have looked at the last item in the data
            boolean hitLast;

            // Get the item ID locally (instead of getItemIdAtPosition), so
            // we need the adapter
            T adapter = getAdapter();
            if (adapter == null) {
                return INVALID_POSITION;
            }

            while (SystemClock.uptimeMillis() <= endTime) {
                rowId = adapter.getItemId(seed);
                if (rowId == idToMatch) {
                    // Found it!
                    return seed;
                }

                hitLast = last == count - 1;
                hitFirst = first == 0;

                if (hitLast && hitFirst) {
                    // Looked at everything
                    break;
                }

                if (hitFirst || (next && !hitLast)) {
                    // Either we hit the top, or we are trying to move down
                    last++;
                    seed = last;
                    // Try going up next time
                    next = false;
                } else if (hitLast || (!next && !hitFirst)) {
                    // Either we hit the bottom, or we are trying to move up
                    first--;
                    seed = first;
                    // Try going down next time
                    next = true;
                }

            }

            return INVALID_POSITION;
        }

        /**
1124         * Find a position that can be selected (i.e., is not a separator).
1125         *
1126         * @param position The starting position to look at.
1127         * @param lookDown Whether to look down for other positions.
1128         * @return The next selectable position starting at position and then searching either up or
1129         *         down. Returns {@link #INVALID_POSITION} if nothing can be found.
1130         */
        int lookForSelectablePosition(int position, boolean lookDown) {
            return position;
        }

        /**
1136         * Utility to keep mSelectedPosition and mSelectedRowId in sync
1137         * @param position Our current position
1138         */
        void setSelectedPositionInt(int position) {
            mSelectedPosition = position;
            mSelectedRowId = getItemIdAtPosition(position);
        }

        /**
1145         * Utility to keep mNextSelectedPosition and mNextSelectedRowId in sync
1146         * @param position Intended value for mSelectedPosition the next time we go
1147         * through layout
1148         */
        void setNextSelectedPositionInt(int position) {
            mNextSelectedPosition = position;
            mNextSelectedRowId = getItemIdAtPosition(position);
            // If we are trying to sync to the selection, update that too
            if (mNeedSync && mSyncMode == SYNC_SELECTED_POSITION
                    && position >= 0) {
                mSyncPosition = position;
                mSyncRowId = mNextSelectedRowId;
            }
        }

        /**
1161         * Remember enough information to restore the screen state when the data has
1162         * changed.
1163         *
1164         */
        void rememberSyncState() {
            if (getChildCount() > 0) {
                mNeedSync = true;
                if (mIsVertical) {
                    mSyncSize = mLayoutHeight;
                } else {
                    mSyncSize = mLayoutWidth;
                }
                if (mSelectedPosition >= 0) {
                    // Sync the selection state
                    View v = getChildAt(mSelectedPosition - mFirstPosition);
                    mSyncRowId = mNextSelectedRowId;
                    mSyncPosition = mNextSelectedPosition;
                    if (v != null) {
                        if (mIsVertical) {
                            mSpecificTop = v.getTop();
                        } else {
                            mSpecificTop = v.getLeft();
                        }
                    }
                    mSyncMode = SYNC_SELECTED_POSITION;
                } else {
                    // Sync the based on the offset of the first view
                    View v = getChildAt(0);
                    T adapter = getAdapter();
                    if (mFirstPosition >= 0
                            && mFirstPosition < adapter.getCount()) {
                        mSyncRowId = adapter.getItemId(mFirstPosition);
                    } else {
                        mSyncRowId = NO_ID;
                    }
                    mSyncPosition = mFirstPosition;
                    if (v != null) {
                        if (mIsVertical) {
                            mSpecificTop = v.getTop();
                        } else {
                            mSpecificTop = v.getLeft();
                        }
                    }
                    mSyncMode = SYNC_FIRST_POSITION;
                }
            }
        }

        /**
1210         * Offset the vertical location of all children of this view by the specified number of pixels.
1211         *
1212         * @param offset the number of pixels to offset
1213         *
1214         */
        public void offsetChildrenTopAndBottom(int offset) {
            final int count = getChildCount();

            for (int i = 0; i < count; i++) {
                final View v = getChildAt(i);
                v.offsetTopAndBottom(offset);
            }
        }

        /**
1225         * Offset the horizontal location of all children of this view by the specified number of pixels.
1226         *
1227         * @param offset the number of pixels to offset
1228         *
1229         */
        public void offsetChildrenLeftAndRight(int offset) {
            final int count = getChildCount();

            for (int i = 0; i < count; i++) {
                final View v = getChildAt(i);
                v.offsetLeftAndRight(offset);
            }
        }

        protected void setIsVertical(boolean vertical) {
            mIsVertical = vertical;
        }

        protected boolean isVertical() {
            return mIsVertical;
        }

    }