/*
 * Copyright 2013 http4s.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.http4s

package parser

import org.http4s.syntax.header._
import org.http4s.headers.`Content-Language`

class ContentLanguageSpec extends Http4sSuite {

  def parse(value: String): ParseResult[`Content-Language`] =
    `Content-Language`.parse(value)

  val en = `Content-Language`(LanguageTag("en"))
  val en_IN = `Content-Language`(LanguageTag("en", "IN"))
  val en_IN_en_US = `Content-Language`(LanguageTag("en", "IN"), LanguageTag("en", "US"))
  val multi_lang =
    `Content-Language`(LanguageTag("en"), LanguageTag("fr"), LanguageTag("da"), LanguageTag("id"))

  test("Content-Language should Give correct value") {
    assertEquals(en.value, "en")
    assertEquals(en_IN.value, "en-IN")
    assertEquals(en_IN_en_US.value, "en-IN, en-US")
    assertEquals(multi_lang.value, "en, fr, da, id")
  }

  test("Content-Language should Parse Properly") {
    assertEquals(parse(en.value), Right(en))
    assertEquals(parse(en_IN.value), Right(en_IN))
    assertEquals(parse(en_IN_en_US.value), Right(en_IN_en_US))
    assertEquals(parse(multi_lang.value), Right(multi_lang))
  }

}
