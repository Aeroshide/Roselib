package com.aeroshide.rose_bush.gui;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.MutableComponent;

public class IntFieldWidget extends EditBox {
    public IntFieldWidget(Font textRenderer, int x, int y, int width, int height, MutableComponent message) {
        super(textRenderer, x, y, width, height, message);
    }

    public void insertText(String text) {
        String oldText = this.getValue();
        super.insertText(text);
        String newText = this.getValue();

        while (newText.length() > 1 && newText.startsWith("0")) {
            newText = newText.substring(1);
        }

        try {
            long number = Long.parseLong(newText);
        } catch (NumberFormatException e) {
            this.setValue(oldText);
            return;
        }
        this.setValue(newText);
    }


    public int getInt() {
        String text = this.getValue();
        if (text == null || text.isEmpty()) {
            return 0;
        }
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }


}
