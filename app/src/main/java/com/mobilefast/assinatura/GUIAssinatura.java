package com.mobilefast.assinatura;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;

import sfa.android.ControllerSFAndroid;
import sfa.android.CoreSFAndroid;


public class GUIAssinatura extends GraphicsActivity {//implements ColorPickerDialog.OnColorChangedListener {
    private static final int COLOR_MENU_ID = Menu.FIRST;
    private static final int EMBOSS_MENU_ID = Menu.FIRST + 1;
    private static final int BLUR_MENU_ID = Menu.FIRST + 2;
    private static final int ERASE_MENU_ID = Menu.FIRST + 3;
    private static final int SRCATOP_MENU_ID = Menu.FIRST + 4;
    private static final int STOP_MENU_ID = Menu.FIRST + 5;
    public static boolean IS_REPAINT_ASSINATURA = true;
    static File sdcard = Environment.getExternalStorageDirectory();
    public final static File SDDIRETORIO = new File(sdcard.getAbsolutePath() + "/SFAndroid/Assinatura");
    private Paint mPaint;
    private MaskFilter mEmboss;
    private MaskFilter mBlur;
    private Bitmap mBitmap;
    private boolean isSalvar = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (IS_REPAINT_ASSINATURA) {
            setContentView(new MyView(this));
            IS_REPAINT_ASSINATURA = true;
        }

        this.setTitle("Assinatura");

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        //mPaint.setColor(0xFFFF0000);
        // LETRA
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(3);

        mEmboss = new EmbossMaskFilter(new float[]{1, 1, 1}, 0.4f, 6, 3.5f);
        mBlur = new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL);
    }

    public void colorChanged(int color) {
        mPaint.setColor(color);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0, COLOR_MENU_ID, 0, "Salvar").setShortcut('3', 'c');
        menu.add(0, EMBOSS_MENU_ID, 0, "Font Size 1").setShortcut('4', 's');
        menu.add(0, BLUR_MENU_ID, 0, "Font Size 2").setShortcut('5', 'z');
        menu.add(0, ERASE_MENU_ID, 0, "Font Size 3").setShortcut('5', 'z');
        //menu.add(0, SRCATOP_MENU_ID, 0, "SrcATop").setShortcut('5', 'z');
        menu.add(0, STOP_MENU_ID, 0, "Limpar").setShortcut('5', 'z');

        /****   Is this the mechanism to extend with filter effects?
         Intent intent = new Intent(null, getIntent().getData());
         intent.addCategory(Intent.CATEGORY_ALTERNATIVE);
         menu.addIntentOptions(
         Menu.ALTERNATIVE, 0,
         new ComponentName(this, NotesList.class),
         null, intent, 0, null);
         *****/
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mPaint.setXfermode(null);
        mPaint.setAlpha(0xFF);

        switch (item.getItemId()) {
            case COLOR_MENU_ID:
                isSalvar = false;
                this.salvarAssinatura();
                return true;
            case EMBOSS_MENU_ID:
                mPaint.setStrokeWidth(3);

//			if (mPaint.getMaskFilter() != mEmboss) {
//				mPaint.setMaskFilter(mEmboss);
//			} else {
//				mPaint.setMaskFilter(null);
//			}
                return true;

            case BLUR_MENU_ID:
                mPaint.setStrokeWidth(6);
//			if (mPaint.getMaskFilter() != mBlur) {
//				mPaint.setMaskFilter(mBlur);
//			} else {
//				mPaint.setMaskFilter(null);
//			}
                return true;

            case ERASE_MENU_ID:
                mPaint.setStrokeWidth(12);
                //mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
                return true;

            case SRCATOP_MENU_ID:
                mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
                mPaint.setAlpha(0x80);
                return true;

            case STOP_MENU_ID:
                isSalvar = false;

                CoreSFAndroid.getintance().abrirTelaGUIAssinatura();
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void salvarArquivoJPEG(Bitmap bmp, String nome) {
        try {

            String path = SDDIRETORIO.getAbsolutePath();
            File f = new File(path);
            if (!f.exists()) {
                f.mkdirs();
            }
            String nome_ass = nome + CoreSFAndroid.codigo_vendedor + ".jpg";

            path = CoreSFAndroid.NOME_DIRETORIO_ASS + "/" + nome_ass;
            //path = SDDIRETORIO.getAbsolutePath()+"/"+""+new Date().getTime()+"ass.jpeg";
            f = new File(path);
            FileOutputStream out = new FileOutputStream(f);
            bmp.compress(Bitmap.CompressFormat.JPEG, 90, out);
            CoreSFAndroid.enviar_Assinatura("" + CoreSFAndroid.NOME_DIRETORIO_ASS + "/", nome_ass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void salvarAssinatura() {
        System.out.println("Salvando assinatura...");
        if (mBitmap != null) {
            salvarArquivoJPEG(mBitmap, CoreSFAndroid.codigo_cotacao);
            CoreSFAndroid.getintance().abrirTelaPrincipalCliente();
            finish();
        } else {
            ControllerSFAndroid.getInstancia().showMensagemSimples("Tentar novamente");
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Deseja realmente sair da assinatura?")
                    .setCancelable(false)
                    .setIcon(android.R.drawable.ic_dialog_alert) // ícone de alerta
                    .setTitle("Atenção:") //título do caixa de diálogo

                    //Evento disparado se clicar no botão Sim
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            CoreSFAndroid.flag_grupo = 1;
                            // CoreSFAndroid.getintance().abrirTelaPrincipalPedido();
                            finish();
                        }
                    })

                    //Event disparado se clicar no botão Não
                    .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel(); //Cancela a caixa de diálogo e volta a tela anterior
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show(); //Chama a caixa de diálogo
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public class MyView extends View {

        private static final float MINP = 0.25f;
        private static final float MAXP = 0.75f;
        private static final float TOUCH_TOLERANCE = 4;
        private Canvas mCanvas;
        private Path mPath;
        private Paint mBitmapPaint;
        private float mX, mY;

        public MyView(Context c) {
            super(c);

            mPath = new Path();
            mBitmapPaint = new Paint(Paint.DITHER_FLAG);

            try {
                String path = SDDIRETORIO.getAbsolutePath();//CoreEntregaExpress.URL_WORK_IMG;
                File f = new File(path);
                if (!f.exists()) {
                    f.mkdirs();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            // FUNDO
            canvas.drawColor(Color.BLACK);// fundo
            canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
            canvas.drawPath(mPath, mPaint);
        }

        private void touch_start(float x, float y) {
            mPath.reset();
            mPath.moveTo(x, y);
            mX = x;
            mY = y;
        }

        private void touch_move(float x, float y) {
            float dx = Math.abs(x - mX);
            float dy = Math.abs(y - mY);
            if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
                mX = x;
                mY = y;
            }
        }

        private void touch_up() {
            mPath.lineTo(mX, mY);
            // commit the path to our offscreen
            mCanvas.drawPath(mPath, mPaint);
            // kill this so we don't double draw
            mPath.reset();
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touch_start(x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_MOVE:
                    touch_move(x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    touch_up();
                    invalidate();
                    break;
            }
            return true;
        }
    }
}
