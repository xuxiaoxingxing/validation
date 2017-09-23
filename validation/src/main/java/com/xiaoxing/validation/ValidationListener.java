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

import android.support.annotation.NonNull;

/**
 * Defines the interface, a class, which should be notified, when a view has been validated, must
 * implement.
 *
 * @param <Type>
 *         The type of the values, which should be validated
 * @author Michael Rapp
 * @since 1.0.0
 */
public interface ValidationListener<Type> {

    /**
     * The method, which is invoked, when a validation succeeded.
     *
     * @param view
     *         The view, whose value has been validated, as an instance of the type {@link
     *         Validateable}
     */
    void onValidationSuccess(@NonNull Validateable<Type> view);

    /**
     * The method, which is invoked, when a validation failed.
     *
     * @param view
     *         The view, whose value has been validated, as an instance of the type {@link
     *         Validateable}
     * @param validator
     *         The validator, which has been failed, as an instance of the type {@link Validator}
     */
    void onValidationFailure(@NonNull Validateable<Type> view, @NonNull Validator<Type> validator);

}