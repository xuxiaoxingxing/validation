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
package de.mrapp.android.validation.validators.text;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.mrapp.android.validation.validators.AbstractValidator;

import static de.mrapp.android.util.Condition.ensureNotNull;

/**
 * A validator, which allows to validate texts to ensure, that they only contain letters or numbers.
 * It is possible to specify, whether only uppercase or lowercase letters should be accepted, or if
 * they should be threatened case-insensitive. Letters are considered to be all alphabetical
 * characters from A to B. Additionally, it is possible to specify whether spaces should be allowed
 * and to add special characters, which should also be accepted. Empty texts are also accepted.
 *
 * @author Michael Rapp
 * @since 1.0.0
 */
public class LetterOrNumberValidator extends AbstractValidator<CharSequence> {

    /**
     * The regular expression, which is used, when only uppercase letters and numbers should be
     * allowed.
     */
    private static final Pattern UPPERCASE_PATTERN = Pattern.compile("^[A-Z0-9]*$");

    /**
     * The regular expression, which is used, when only lowercase letters and numbers should be
     * allowed.
     */
    private static final Pattern LOWERCASE_PATTERN = Pattern.compile("^[a-z0-9]*$");

    /**
     * The regular expression, which is used, when all letters and numbers, regardless of their
     * case, should be allowed.
     */
    private static final Pattern CASE_INSENSITIVE_PATTERN = Pattern.compile("^[a-zA-Z0-9]*$");

    /**
     * The case sensitivity, which is used by the validator.
     */
    private Case caseSensitivity;

    /**
     * True, if spaces should be allowed, false otherwise.
     */
    private boolean allowSpaces;

    /**
     * An array, which contains allowed special characters.
     */
    private char[] allowedCharacters;

    /**
     * Creates a new validator, which allows to validate texts to ensure, that they only contain
     * letters or numbers.
     *
     * @param errorMessage
     *         The error message, which should be shown, if the validation fails, as an instance of
     *         the type {@link CharSequence}. The error message may not be null
     * @param caseSensitivity
     *         The case senstivitiy, which should be used by the validator, as a value of the enum
     *         {@link Case}. The value may either be <code>UPPERCASE</code>, <code>LOWERCASE</code>
     *         or <code>CASE_INSENSITIVE</code>
     * @param allowSpaces
     *         True, if spaces should be allowed, false otherwise
     * @param allowedCharacters
     *         The allowed special characters as an array of the type <code>char</code>. The array
     *         may not be null
     */
    public LetterOrNumberValidator(@NonNull final CharSequence errorMessage,
                                   @NonNull final Case caseSensitivity, final boolean allowSpaces,
                                   @NonNull final char... allowedCharacters) {
        super(errorMessage);
        setCaseSensitivity(caseSensitivity);
        allowSpaces(allowSpaces);
        setAllowedCharacters(allowedCharacters);
    }

    /**
     * Creates a new validator, which allows to validate texts to ensure, that they only contain
     * letters or numbers.
     *
     * @param context
     *         The context, which should be used to retrieve the error message, as an instance of
     *         the class {@link Context}. The context may not be null
     * @param resourceId
     *         The resource ID of the string resource, which contains the error message, which
     *         should be set, as an {@link Integer} value. The resource ID must correspond to a
     *         valid string resource
     * @param caseSensitivity
     *         The case senstivitiy, which should be used by the validator, as a value of the enum
     *         {@link Case}. The value may either be <code>UPPERCASE</code>, <code>LOWERCASE</code>
     *         or <code>CASE_INSENSITIVE</code>
     * @param allowSpaces
     *         True, if spaces should be allowed, false otherwise
     * @param allowedCharacters
     *         The allowed special characters as an array of the type <code>char</code>. The array
     *         may not be null
     */
    public LetterOrNumberValidator(@NonNull final Context context, @StringRes final int resourceId,
                                   @NonNull final Case caseSensitivity, final boolean allowSpaces,
                                   @NonNull final char... allowedCharacters) {
        super(context, resourceId);
        setCaseSensitivity(caseSensitivity);
        allowSpaces(allowSpaces);
        setAllowedCharacters(allowedCharacters);
    }

    /**
     * Returns the case sensitivity, which is used by the validator.
     *
     * @return The case sensitivity, which is used by the validator, as a value of the enum {@link
     * Case}. The value may either be <code>UPPERCASE</code>, <code>LOWERCASE</code> or
     * <code>CASE_INSENSITIVE</code>
     */
    public final Case getCaseSensitivity() {
        return caseSensitivity;
    }

    /**
     * Sets the case sensitivity, which should be used by the validator.
     *
     * @param caseSensitivty
     *         The case senstivitiy, which should be set, as a value of the enum {@link Case}. The
     *         value may either be <code>UPPERCASE</code>, <code>LOWERCASE</code> or
     *         <code>CASE_INSENSITIVE</code>
     */
    public final void setCaseSensitivity(@NonNull final Case caseSensitivty) {
        ensureNotNull(caseSensitivty, "The case sensitivity may not be null");
        this.caseSensitivity = caseSensitivty;
    }

    /**
     * Returns, whether spaces are allowed, or not.
     *
     * @return True, if spaces are allowed, false otherwise
     */
    public final boolean areSpacesAllowed() {
        return allowSpaces;
    }

    /**
     * Sets, whether spaces should be allowed, or not.
     *
     * @param allowSpaces
     *         True, if spaces should be allowed, false otherwise
     */
    public final void allowSpaces(final boolean allowSpaces) {
        this.allowSpaces = allowSpaces;
    }

    /**
     * Returns the allowed special characters.
     *
     * @return An array, which contains the allowed special characters, as an array of the type
     * <code>char</code>
     */
    public final char[] getAllowedCharacters() {
        return allowedCharacters;
    }

    /**
     * Sets the allowed special characters.
     *
     * @param allowedCharacters
     *         The allowed special characters, which should be set, as an array of the type
     *         <code>char</code>. The array may not be null
     */
    public final void setAllowedCharacters(@NonNull final char[] allowedCharacters) {
        ensureNotNull(allowedCharacters, "The array may not be null");
        this.allowedCharacters = allowedCharacters;
    }

    @Override
    public final boolean validate(final CharSequence value) {
        String text = value.toString();
        Pattern regex = CASE_INSENSITIVE_PATTERN;

        if (areSpacesAllowed()) {
            text = text.replaceAll("\\s+", "");
        }

        for (char character : getAllowedCharacters()) {
            text = text.replaceAll(String.valueOf(character), "");
        }

        if (getCaseSensitivity() == Case.UPPERCASE) {
            regex = UPPERCASE_PATTERN;
        } else if (getCaseSensitivity() == Case.LOWERCASE) {
            regex = LOWERCASE_PATTERN;
        }

        Matcher matcher = regex.matcher(text);
        return matcher.matches();
    }

}