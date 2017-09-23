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
package com.xiaoxing.validation.constraints.text;

import java.util.regex.Pattern;

/**
 * A constraint, which allows to verify texts in order to check, if they contain at least one
 * letter.
 *
 * @author Michael Rapp
 * @since 1.0.0
 */
public class ContainsLetterConstraint extends RegexConstraint {

    /**
     * The regular expression, which is used by the constraint.
     */
    private static final Pattern REGEX = Pattern.compile("(.)*([a-zA-Z])(.)*");

    /**
     * Creates a new constraint, which allows to verify texts in order to check, if they contain at
     * least one letter.
     */
    public ContainsLetterConstraint() {
        super(REGEX);
    }

}