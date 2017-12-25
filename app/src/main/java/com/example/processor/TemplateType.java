package com.example.processor;

/**
 * Created by coachroebuck on 12/24/17.
 *
 */

public enum TemplateType {
    NewYork, Orange, Yellow, Green;

    //traverse through the list of options
    public TemplateType next() {
        return ordinal() == values().length - 1 ? NewYork : values()[ordinal() + 1];
    }

    public TemplateType previous() {
        return ordinal() == 0 ? Green : values()[values().length - 1];
    }

    //link each image resource with the template value.
    public int getImageResource(TemplateType templateType) {
        switch (templateType) {
            case NewYork:
                return R.drawable.img0;
            case Orange:
                return R.drawable.img1;
            case Yellow:
                return R.drawable.img2;
            case Green:
                return R.drawable.img3;
            default:
                return R.drawable.img0;
        }
    }
}
