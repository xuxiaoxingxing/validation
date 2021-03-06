/*
 * Copyright 2015 - 2017 Michael Rapp
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.xiaoxing.validation;

import android.graphics.drawable.Drawable;

/**
 * Defines the interface, a class, which should be able to validate values of a specific type, must
 * implement.
 *
 * @param <Type>
 *         The type of the values, which should be validated
 * @author Michael Rapp
 * @since 1.0.0
 */
public interface Validator<Type> {

    /**
     * Validates a specific value.
     *
     * @param value
     *         The value, which should be validated, as an instance of the generic type Type
     * @return True, if the validation succeeded, false otherwise
     */
    boolean validate(Type value);

    /**
     * Returns the error message, which should be shown, if the validation fails.
     *
     * @return The error message, which should be shown, if the validation fails, as an instance of
     * the type {@link CharSequence}. The error message may not be null
     */
    CharSequence getErrorMessage();

    /**
     * Returns the icon, which should be shown, if the validation fails.
     *
     * @return The icon, which should be shown, if the validation fails, as an instance of the class
     * {@link Drawable} or null, if no icon should be shown
     */
    Drawable getIcon();

}