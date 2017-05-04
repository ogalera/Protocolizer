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

package cat.ogasoft.examples;

import cat.ogasoft.protocolizer.annotations.ProtoFileV2;
import java.util.Collection;
import java.util.List;

/**
 * @author Oscar Galera i Alfaro
 * @date   Apr 27, 2017 [9:43:09 PM]
 *
 * @brief DESCRIPTION
 */
//@ProtoFileV2.File(pJavaName = "prova", generateSource = true)
//@ProtoFileV2.Dumpper
public class Test2 {
    
    @ProtoFileV2.File.Message
    public static class M1{
        @ProtoFileV2.File.Message.Field(comment = "prova", label = ProtoFileV2.File.Message.Field.Label.REPEATED)
        private List<Integer> llista;

        public Collection<Integer> getLlista() {
            return llista;
        }

        public void setLlista(List<Integer> llista) {
            this.llista = llista;
        }
    }
    
}
