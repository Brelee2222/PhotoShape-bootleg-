interface ColorChanger {
   int filter(int color);
}

class ColorInvert implements ColorChanger{

    @Override
    public int filter(int color) {
        return color ^ 0xffffff;
    }
}

class Grayify implements ColorChanger {

    @Override
    public int filter(int color) {
        int b = color&0xff;
        int g = color>>8&0xff;
        int r = color>>16&0xff;
        int avg = averageInt(r, g, b);
        color = 0 ^ avg ^ avg<<8 ^ avg<<16;
        return color;
    }

    public int averageInt(int i1, int i2, int i3) {
        //time for averaging through division: 219 ms

        //time for averaging through binary: 204 ms
        //Not as accurate as normal averaging
        int i4 = i1 + i2;
        // int length 32 bits
        i4 >>=1;
        i4 += i3;
        return i4>>1;
    }
}
