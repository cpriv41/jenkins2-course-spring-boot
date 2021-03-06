/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.test.autoconfigure.web.servlet;

import com.gargoylesoftware.htmlunit.WebClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

/**
 * Tests for {@link WebMvcTest} with {@link AutoConfigureMockMvc}.
 *
 * @author Phillip Webb
 */
@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc(addFilters = false, webClientEnabled = false, webDriverEnabled = false)
public class WebMvcTestWithAutoConfigureMockMvcIntegrationTests {

	@Autowired
	private ApplicationContext context;

	@Autowired
	private MockMvc mvc;

	@Test
	public void shouldNotAddFilters() throws Exception {
		this.mvc.perform(get("/one")).andExpect(header().doesNotExist("x-test"));
	}

	@Test
	public void shouldNotHaveWebDriver() throws Exception {
		this.context.getBean(WebDriver.class);
	}

	@Test
	public void shouldNotHaveWebClient() throws Exception {
		this.context.getBean(WebClient.class);
	}

}
