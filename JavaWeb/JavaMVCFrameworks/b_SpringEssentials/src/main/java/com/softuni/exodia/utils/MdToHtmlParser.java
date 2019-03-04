package com.softuni.exodia.utils;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.options.MutableDataSet;

public class MdToHtmlParser {

    private final MutableDataSet options;
    private final Parser parser;
    private final HtmlRenderer renderer;

    public MdToHtmlParser() {
        this.options = new MutableDataSet();

        this.parser = Parser.builder(options).build();
        this.renderer = HtmlRenderer.builder(options).build();
    }

    public String parse(String input){

        return renderer.render(this.parser.parse(input));
    }
}
