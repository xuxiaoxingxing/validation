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
package de.mrapp.android.validation.validators;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import de.mrapp.android.validation.Validator;

import static de.mrapp.android.util.Condition.ensureAtLeast;
import static de.mrapp.android.util.Condition.ensureNotNull;

/**
 * A validator, which allows to combine multiple validators in a disjunctive manner. If at least one
 * validator succeeds, the resulting validator will also succeed.
 *
 * @param <Type>
 *         The type of the values, which should be validated
 * @author Michael Rapp
 * @since 1.0.0
 */
public class DisjunctiveValidator<Type> extends AbstractValidator<Type> {

    /**
     * A array, which contains the single validators, the validator consists of.
     */
    private Validator<Type>[] validators;

    /**
     * Creates a new validator, which allows to combine multiple validators in a disjunctive
     * manner.
     *
     * @param errorMessage
     *         The error message, which should be shown, if the validation fails, as an instance of
     *         the type {@link CharSequence}. The error message may not be null
     * @param validators
     *         The single validators, the validator should consist of, as an array of the type
     *         {@link Validator}. The validators may neither be null, nor empty
     */
    @SafeVarargs
    public DisjunctiveValidator(@NonNull final CharSequence errorMessage,
                                @NonNull final Validator<Type>... validators) {
        super(errorMessage);
        setValidators(validators);
    }

    /**
     * Creates a new validator, which allows to combine multiple validators in a disjunctive
     * manner.
     *
     * @param context
     *         The context, which should be used to retrieve the error message, as an instance of
     *         the class {@link Context}. The context may not be null
     * @param resourceId
     *         The resource ID of the string resource, which contains the error message, which
     *         should be set, as an {@link Integer} value. The resource ID must correspond to a
     *         valid string resource
     * @param validators
     *         The single validators, the validator should consist of, as an array of the type
     *         {@link Validator}. The validators may neither be null, nor empty
     */
    @SafeVarargs
    public DisjunctiveValidator(@NonNull final Context context, @StringRes final int resourceId,
                                @NonNull final Validator<Type>... validators) {
        super(context, resourceId);
        setValidators(validators);
    }

    /**
     * Creates and returns a validator, which allows to combine multiple validators in a disjunctive
     * manner.
     *
     * @param <Type>
     *         The type of the values, which should be validated
     * @param errorMessage
     *         The error message, which should be shown, if the validation fails, as an instance of
     *         the type {@link CharSequence}. The error message may not be null
     * @param validators
     *         The single validators, the validator should consist of, as an array of the type
     *         {@link Validator}. The validators may neither be null, nor empty
     * @return The validator, which has been created, as an instance of the class {@link
     * DisjunctiveValidator}
     */
    @SafeVarargs
    public static <Type> DisjunctiveValidator<Type> create(@NonNull final CharSequence errorMessage,
                                                           @NonNull final Validator<Type>... validators) {
        return new DisjunctiveValidator<>(errorMessage, validators);
    }

    /**
     * Creates and returns a validator, which allows to combine multiple validators in a disjunctive
     * manner.
     *
     * @param <Type>
     *         The type of the values, which should be validated
     * @param context
     *         The context, which should be used to retrieve the error message, as an instance of
     *         the class {@link Context}. The context may not be null
     * @param resourceId
     *         The resource ID of the string resource, which contains the error message, which
     *         should be set, as an {@link Integer} value. The resource ID must correspond to a
     *         valid string resource
     * @param validators
     *         The single validators, the validator should consist of, as an array of the type
     *         {@link Validator}. The validators may neither be null, nor empty
     * @return The validator, which has been created, as an instance of the class {@link
     * DisjunctiveValidator}
     */
    @SafeVarargs
    public static <Type> DisjunctiveValidator<Type> create(@NonNull final Context context,
                                                           @StringRes final int resourceId,
                                                           @NonNull final Validator<Type>... validators) {
        return new DisjunctiveValidator<>(context, resourceId, validators);
    }

    /**
     * Returns the single validators, the validator consists of.
     *
     * @return The single validators, the validator consists of, as an array of the type {@link
     * Validator}
     */
    public final Validator<Type>[] getValidators() {
        return validators;
    }

    /**
     * Sets the single validators, the validator should consist of.
     *
     * @param validators
     *         The single validators, which should be set, as an array of the type {@link
     *         Validator}. The validators may neither be null, nor empty
     */
    @SafeVarargs
    public final void setValidators(@NonNull final Validator<Type>... validators) {
        ensureNotNull(validators, "The validators may not be null");
        ensureAtLeast(validators.length, 1, "The validators may not be empty");
        this.validators = validators;
    }

    @Override
    public final boolean validate(final Type value) {
        for (Validator<Type> validator : validators) {
            if (validator.validate(value)) {
                return true;
            }
        }

        return false;
    }

}