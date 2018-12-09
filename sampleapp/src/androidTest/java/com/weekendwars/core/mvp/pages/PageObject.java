package com.weekendwars.core.mvp.pages;

import com.weekendwars.core.mvp.validators.Validator;

import javax.annotation.Nullable;

/* default */ class PageObject {

    /* default */ PageObject(@Nullable final Validator validator) {
        if (validator != null) {
            validator.validate();
        }
    }
}
