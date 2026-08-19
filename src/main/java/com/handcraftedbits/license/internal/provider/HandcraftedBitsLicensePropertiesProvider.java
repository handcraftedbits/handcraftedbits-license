/**
 * Copyright (C) 2016-2026 HandcraftedBits
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.handcraftedbits.license.internal.provider;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.mycila.maven.plugin.license.AbstractLicenseMojo;
import com.mycila.maven.plugin.license.PropertiesProvider;
import com.mycila.maven.plugin.license.document.Document;

/**
 * Provides license property adjustments for the mycila Maven license plugin by computing a dynamic year range based on the project inception year and
 * the current calendar year.
 */
public final class HandcraftedBitsLicensePropertiesProvider implements PropertiesProvider {
     /**
      * Creates a new HandcraftedBitsLicensePropertiesProvider.
      */
     public HandcraftedBitsLicensePropertiesProvider() {
     }

     @Override
     @SuppressWarnings("unused")
     public Map<String, String> adjustProperties (final AbstractLicenseMojo mojo,
          final Map<String, String> currentProperties, final Document document) {
          int currentYear = Calendar.getInstance().get(Calendar.YEAR);
          int inceptionYear;
          final Map<String, String> result = new HashMap<>();

          try {
               inceptionYear = Integer.parseInt(currentProperties.get("project.inceptionYear"));
          }

          catch (final Throwable e) {
               inceptionYear = 0;
          }

          result.put("yearRange", (inceptionYear == currentYear) ? "" + currentYear : inceptionYear + "-" +
               currentYear);

          return result;
     }
}
