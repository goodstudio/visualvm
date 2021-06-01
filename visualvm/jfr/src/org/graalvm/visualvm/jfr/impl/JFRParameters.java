/*
 * Copyright (c) 2021, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package org.graalvm.visualvm.jfr.impl;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jiri Sedlacek
 */
final class JFRParameters {
    
    static final String NAME = "name";                                          // NOI18N
    static final String SETTINGS = "settings";                                  // NOI18N
    
    
    private final Map<String, String> parameters;
    
    
    private JFRParameters(String parametersS) {
        if (parametersS == null || parametersS.isEmpty()) {
            parameters = null;
        } else {
            parameters = new HashMap();
            parseParameters(parametersS, parameters);
        }
    }
    
    
    static JFRParameters parse(String parameters) {
        return new JFRParameters(parameters);
    }
    
    
    String get(String key) {
        return parameters == null ? null : parameters.get(key);
    }
    
    
    public String toString() {
        return parameters == null ? "[no parameters]" : parameters.toString();  // NOI18N
    }
    
    
    private static void parseParameters(String parametersS, Map<String, String> parameters) {
        for (String parameter : parametersS.split(",")) {                       // NOI18N
            
            // name
            int idx = parameter.indexOf(NAME + "=");                            // NOI18N
            if (idx == 0) parameters.put(NAME, parameter.substring(NAME.length() + 1));
            
            // settings
            idx = parameter.indexOf(SETTINGS + "=");                            // NOI18N
            if (idx == 0) parameters.put(SETTINGS, parameter.substring(SETTINGS.length() + 1));
            
        }
    }
    
}
