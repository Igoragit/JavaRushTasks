package com.javarush.task.task39.task3905;

public class PhotoPaint {
    public boolean paintFill(Color[][] image, int x, int y, Color desiredColor) {

        if (x < 0 || y < 0 || x >= image.length || y >= image[x].length)
            return false;
        if (image[y][x].equals(desiredColor))
            return false;

        Color currentColor = image[y][x];
        paintFill(image, y, x, desiredColor, currentColor);
        return true;

    }

    private void paintFill(Color[][] image, int y, int x, Color desiredColor, Color currentColor) {
        if (y < 0 || x < 0 || y >= image.length || x >= image[0].length)  return;

        if (image[y][x] != currentColor) return;
        else  image[y][x] = desiredColor;

        paintFill(image, y - 1, x, desiredColor, currentColor);
        paintFill(image, y + 1 , x, desiredColor, currentColor);
        paintFill(image, y, x - 1, desiredColor, currentColor);
        paintFill(image, y, x + 1, desiredColor, currentColor);
    }
}
