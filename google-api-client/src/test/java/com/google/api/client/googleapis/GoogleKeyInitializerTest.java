/*
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

package com.google.api.client.googleapis;

import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.testing.http.HttpTesting;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.util.Key;

import junit.framework.TestCase;

/**
 * Tests {@link GoogleKeyInitializer}.
 *
 * @author Yaniv Inbar
 */
public class GoogleKeyInitializerTest extends TestCase {

  public static class MyClient extends AbstractGoogleClient {

    public MyClient(HttpTransport transport) {
      super(transport, null, HttpTesting.SIMPLE_URL, "test/", null);
    }
  }

  public static class MyRequest extends AbstractGoogleClientRequest<String> {
    @Key
    String key;

    protected MyRequest(MyClient client, String method, String uriTemplate,
        HttpContent content, Class<String> responseClass) {
      super(client, method, uriTemplate, content, responseClass);
    }
  }

  public void testInitialize() {
    GoogleKeyInitializer key = new GoogleKeyInitializer("foo");
    MyClient client = new MyClient(new MockHttpTransport());
    MyRequest request = new MyRequest(client, "GET", "", null, String.class);
    assertNull(request.key);
    key.initialize(request);
    assertEquals("foo", request.key);
  }
}