package com.aprinz.ecsearch.extractor;

import java.io.IOException;

/**
 * Created by Alexander on 25.07.2015.
 */
public class ExtractorException extends RuntimeException{

    public ExtractorException(Throwable t) {
        super(t);
    }
}
