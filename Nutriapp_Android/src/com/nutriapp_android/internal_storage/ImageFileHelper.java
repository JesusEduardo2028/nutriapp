package com.nutriapp_android.internal_storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

public class ImageFileHelper {
	Context context;
	String estado;
	
	public ImageFileHelper(Context context) {
		this.context = context;
		estado = Environment.getExternalStorageState();
	}
	
	public void setImageFile(Bitmap bitmap, String name) {
		if(estado.equals(Environment.MEDIA_MOUNTED)) {
			try {
	            FileOutputStream out = new FileOutputStream(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)+"/"+name+".png");
	            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
	            out.close();
	            Log.i("imageHelper", "-> archivo almacenado");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
	            e.printStackTrace();
	        }
		}
    }

    public Bitmap getImageFile(String name) {
    	Bitmap bitmap = null;
    	
    	if(estado.equals(Environment.MEDIA_MOUNTED)) {
    		try {
    			File tempFile = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)+"/", name+".png");
    			if(tempFile.exists()) {
    				Log.i("imageHelper", "-> existe archivo");
    				FileInputStream is = new FileInputStream(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)+"/"+name+".png");
                    bitmap = BitmapFactory.decodeStream(is);
                    is.close();
    			}
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    	}
        
        return bitmap;
    }
    
    public void removeImagesFile() {
    	File images = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
		if (images.exists()) {
			images.delete();
		}
	}

}
