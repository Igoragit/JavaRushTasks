package com.javarush.task.task16.task1631;

import com.javarush.task.task16.task1631.common.*;

import static com.javarush.task.task16.task1631.common.ImageTypes.BMP;
import static com.javarush.task.task16.task1631.common.ImageTypes.JPG;
import static com.javarush.task.task16.task1631.common.ImageTypes.PNG;

/**
 * Created by Гор on 2017-02-20.
 */
public class ImageReaderFactory {

    public static ImageReader getImageReader(ImageTypes types){

        if(types!=BMP && types!=JPG && types!=PNG ) throw new IllegalArgumentException();
        else {
            if (types==BMP) return new BmpReader();
            if (types==JPG) return new JpgReader();
            if (types==PNG) return new PngReader();


        }
        return null;
    }
}
