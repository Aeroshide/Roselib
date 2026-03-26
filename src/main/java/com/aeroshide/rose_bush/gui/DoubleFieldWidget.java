package com.aeroshide.rose_bush.gui;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.MutableComponent;

public class DoubleFieldWidget extends EditBox {
    public DoubleFieldWidget(Font textRenderer, int x, int y, int width, int height, MutableComponent message) {
        super(textRenderer, x, y, width, height, message);
    }

    @Override
    public void insertText(String text) {
        String oldText = this.getValue();
        super.insertText(text);
        String newText = this.getValue();

        try {
            double number = Double.parseDouble(newText);
            this.setValue(Double.toString(number));
        } catch (NumberFormatException e) {
            this.setValue(oldText);
        }
    }

    public double getDouble() {
        String text = this.getValue();
        if (text == null || text.isEmpty()) {
            return 0.0;
        }
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}