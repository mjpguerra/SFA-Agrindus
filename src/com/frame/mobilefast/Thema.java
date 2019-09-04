package com.frame.mobilefast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

public class Thema
{
  public static final int VAL_R = 2;
  public static final int VAL_L = 2;
  public static final int VAL_B = 2;
  public static final int VAL_T = 2;
  public static int FONTE_TITULO_BANNER = 15;
  public static int FONTE_CONTEXT = 17;
  public static int FONTE_TITULO = 14;

  public static int FONTE_MENU_TXT = 17;

  public static int SIZE_CAMPO_H = 50;
  public static final int ESPASSADOR_TABLET = 190;
  public static final int ESPASSADOR_DIV = 640;
  public static final int ESPASSADOR_CELULAR = 150;
  public static int BACK_VERMELHO = 255;
  public static int BACK_VERDE = 127;
  public static int BACK_AZUL = 0;

  public static int THEMA_ID_COR_BACKGROND = 6;

  private static final int BACK_THEMA_1 = Color.rgb(255, 127, 10);
  private static final int BACK_THEMA_2 = Color.rgb(0, 0, 255);
  private static final int BACK_THEMA_3 = Color.rgb(165, 42, 42);
  private static final int BACK_THEMA_4 = Color.rgb(0, 0, 0);
  private static final int BACK_THEMA_5 = Color.rgb(33, 94, 33);
  private static final int BACK_THEMA_6 = Color.rgb(100, 100, 100);
  private static final int BACK_THEMA_7 = Color.rgb(5, 5, 5);
  public static final int THEMA_1 = 1;
  public static final int THEMA_2 = 2;
  public static final int THEMA_3 = 3;
  public static final int THEMA_4 = 4;
  public static final int THEMA_5 = 5;
  public static final int THEMA_6 = 6;
  public static final int THEMA_7 = 7;
  public static final int THEMA_DEFAULT = 1;
  public static int AJUSTE_TELA = 50;

  public static int COR_TITULO = -1;
  public static int COR_TITULO_CAMPOS = -1;

  public static int COR_BACKGROND_SUBTITULO = Color.rgb(10, 10, 255);

  public static int COR_BACKGROUND_LIST = -16777216;

  public static int MENU_BAR_H = 50;

  public static int getColorBackGrondThema()
  {
    int thema = THEMA_ID_COR_BACKGROND;

    System.out.println("Thema usado: " + thema);

    if (thema == 1)
      return BACK_THEMA_1;
    if (thema == 2)
      return BACK_THEMA_2;
    if (thema == 3)
      return BACK_THEMA_3;
    if (thema == 4)
      return BACK_THEMA_4;
    if (thema == 5)
      return BACK_THEMA_5;
    if (thema == 6)
      return BACK_THEMA_6;
    if (thema == 7) {
      return BACK_THEMA_7;
    }
    return BACK_THEMA_1;
  }

  public static Bitmap decodeFile(String path)
  {
    return decodeFile(new File(path));
  }

  public static Bitmap decodeFile(String path, int scale) {
    return decodeFile(new File(path), scale);
  }

  public static Bitmap decodeFile(File f)
  {
    try
    {
      BitmapFactory.Options o = new BitmapFactory.Options();
      o.inJustDecodeBounds = true;
      BitmapFactory.decodeStream(new FileInputStream(f), null, o);

      int REQUIRED_SIZE = 70;

      int width_tmp = o.outWidth; int height_tmp = o.outHeight;
      int scale = 1;

      while ((width_tmp / 2 >= 70) && (height_tmp / 2 >= 70))
      {
        width_tmp /= 2;
        height_tmp /= 2;
        scale *= 2;
      }

      BitmapFactory.Options o2 = new BitmapFactory.Options();
      o2.inSampleSize = scale;
      return BitmapFactory.decodeStream(new FileInputStream(f), null, o2); } catch (FileNotFoundException localFileNotFoundException) {
    }
    return null;
  }

  public static Bitmap decodeFile(File f, int scalex)
  {
    try {
      BitmapFactory.Options o = new BitmapFactory.Options();
      o.inJustDecodeBounds = true;
      BitmapFactory.decodeStream(new FileInputStream(f), null, o);

      int REQUIRED_SIZE = scalex;

      int width_tmp = o.outWidth; int height_tmp = o.outHeight;
      int scale = 1;

      while ((width_tmp / 2 >= REQUIRED_SIZE) && (height_tmp / 2 >= REQUIRED_SIZE))
      {
        width_tmp /= 2;
        height_tmp /= 2;
        scale *= 2;
      }

      BitmapFactory.Options o2 = new BitmapFactory.Options();
      o2.inSampleSize = scale;
      return BitmapFactory.decodeStream(new FileInputStream(f), null, o2); } catch (FileNotFoundException localFileNotFoundException) {
    }
    return null;
  }
}