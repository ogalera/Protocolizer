/*
 * Copyright 2017 Oscar Galera i Alfaro.
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
package cat.ogasoft.protocolizer;

import cat.ogasoft.protocolizer.exceptions.DeserializationException;

/**
 * @author Oscar Galera i Alfaro
 * @brief Interface for those class that can deserialize a Protocol Buffer Message to a Java object.
 */
public interface DeserializerProtoWorker<T> {

    /**
     * @pre Data is a valid serialized Protocol Buffer message.
     * @post The serialization process has been started.
     * @param data is the raw representation of the message.
     */
    public void work(byte[] data);

    /**
     * @pre work has been assigned
     * @post actual thread is paused until the deserialization process ends.
     * @return the java representation of the message.
     */
    public T waitUntilEnd() throws InterruptedException, DeserializationException;
}
