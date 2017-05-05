# Protocolizer
<img src="https://github.com/ogalera/protocolizer/blob/master/resources/logo2.png" />
**Zero-overhead Java to Protoc - Protoc to Java** automatizer, at roughly **133Kb**, the library is very light and only works in development pahse to obtain a single contact point with Google Protocol Buffer.

-------------------------------------------------------------------

## Description
Write less and do more **annotating POJO Java classes** that represents a protocol buffer message and left Protocolizer works for you. It's very simple.

### Structure
![esquma](https://github.com/ogalera/protocolizer/blob/master/resources/esquema.png)

### Message Interchange

![esquma](https://github.com/ogalera/protocolizer/blob/master/resources/intercanvi.png)

Note that this library only works at compile time, so your project will never experiment any overhead ad deployment.

-------------------------------------------------------------------
## Example

### Your Java Message
```java
@ProtoFileV2.File(pJavaName = "Example")
@ProtoFileV2.Dumpper
public class Example {

    @ProtoFileV2.File.Message
    public static class Person {

        @ProtoFileV2.File.Message.Field
        private String name;

        @ProtoFileV2.File.Message.Field
        private int id;

        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private String email;

        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.REPEATED)
        private List<PhoneNumber> phones;

        @ProtoFileV2.File.Enum
        public static enum PhoneType {
            MOBILE,
            HOME,
            WORK
        }

        @ProtoFileV2.File.Message
        public static class PhoneNumber {

            @ProtoFileV2.File.Message.Field
            private String number;

            @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
            private PhoneType type;

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public PhoneType getType() {
                return type;
            }

            public void setType(PhoneType type) {
                this.type = type;
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public List<PhoneNumber> getPhones() {
            return phones;
        }

        public void setPhones(List<PhoneNumber> phones) {
            this.phones = phones;
        }

    }

    @ProtoFileV2.File.Message(parallel = true)
    public static class AddressBook {

        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.REPEATED)
        private List<Person> people;

        public List<Person> getPeople() {
            return people;
        }

        public void setPeople(List<Person> people) {
            this.people = people;
        }
    }
}
```


### Google Protocol Buffer message [Autogenerated]
Generated **Google Protocol Buffer message**.

```proto
option java_package = "cat.ogasoft.protocolizer.messages";

option java_outer_classname = "Example";


message Person {
    required string name = 1;
    required int32 id = 2;
    optional string email = 3;
    repeated PhoneNumber phones = 4;
    message PhoneNumber {
        required string number = 1;
        optional PhoneType type = 2;
    }
    enum PhoneType {
        MOBILE = 0;
        HOME = 1;
        WORK = 2;
    }
}

message AddressBook {
    repeated Person people = 1;
}
```

### Java message compiled [Autogenerated]
...

### Serializer [Autogenerated]
```java
package cat.ogasoft.protocolizer.dumppers.serializers;


public class ExampleSerializer {
    public static class Person {
        public byte[] dump(cat.ogasoft.examples.Example.Person target){
            return buildPerson(target).toByteArray();
        }
        public static class PhoneNumber {
            public byte[] dump(cat.ogasoft.examples.Example.Person.PhoneNumber target){
                return buildPhoneNumber(target).toByteArray();
            }
            public static cat.ogasoft.protocolizer.messages.Example.Person.PhoneNumber buildPhoneNumber(cat.ogasoft.examples.Example.Person.PhoneNumber target){
                cat.ogasoft.protocolizer.messages.Example.Person.PhoneNumber.Builder builder = cat.ogasoft.protocolizer.messages.Example.Person.PhoneNumber.newBuilder();
                builder.setNumber(target.getNumber());
                if(target.getType() != null){
                    builder.setType(cat.ogasoft.protocolizer.messages.Example.Person.PhoneType.valueOf(target.getType().name()));
                }
                return builder.build();
            }
        }
        public static cat.ogasoft.protocolizer.messages.Example.Person buildPerson(cat.ogasoft.examples.Example.Person target){
            cat.ogasoft.protocolizer.messages.Example.Person.Builder builder = cat.ogasoft.protocolizer.messages.Example.Person.newBuilder();
            builder.setName(target.getName());
            builder.setId(target.getId());
            if(target.getEmail() != null){
                builder.setEmail(target.getEmail());
            }
            if(target.getPhones() != null){
                if(target.getPhones() != null){
                    for(cat.ogasoft.examples.Example.Person.PhoneNumber k:target.getPhones()){
                        builder.addPhones(cat.ogasoft.protocolizer.dumppers.serializers.ExampleSerializer.Person.PhoneNumber.buildPhoneNumber(k));
                    }
                }
            }
            return builder.build();
        }
    }

    public static class AddressBook extends Thread implements cat.ogasoft.protocolizer.SerializerProtoWorker<cat.ogasoft.examples.Example.AddressBook> {
        private byte[] bytes;
        private cat.ogasoft.examples.Example.AddressBook container;


        @Override
        public void work(cat.ogasoft.examples.Example.AddressBook container) {
            this.container = container;
            super.setName("cat.ogasoft.protocolizer.dumppers.serializers.ExampleSerializer.AddressBookWorker");
            start();
        }

        @Override
        public byte[] waitUntilEnd() throws InterruptedException {
            this.join();
            return bytes;
        }

        @Override
        public void run() {
            bytes = dump(container);
        }
        public byte[] dump(cat.ogasoft.examples.Example.AddressBook target){
            return buildAddressBook(target).toByteArray();
        }
        public static cat.ogasoft.protocolizer.messages.Example.AddressBook buildAddressBook(cat.ogasoft.examples.Example.AddressBook target){
            cat.ogasoft.protocolizer.messages.Example.AddressBook.Builder builder = cat.ogasoft.protocolizer.messages.Example.AddressBook.newBuilder();
            if(target.getPeople() != null){
                if(target.getPeople() != null){
                    for(cat.ogasoft.examples.Example.Person k:target.getPeople()){
                        builder.addPeople(cat.ogasoft.protocolizer.dumppers.serializers.ExampleSerializer.Person.buildPerson(k));
                    }
                }
            }
            return builder.build();
        }
    }

}
```
### Deserializer [Autogenerated]
```Java
package cat.ogasoft.protocolizer.dumppers.deserializers;


public class ExampleDeserializer {
    public static class Person {
        public cat.ogasoft.examples.Example.Person dump(byte[] target) throws com.google.protobuf.InvalidProtocolBufferException {
            cat.ogasoft.protocolizer.messages.Example.Person result = cat.ogasoft.protocolizer.messages.Example.Person.parseFrom(target);
            return buildPerson(result);
        }

        public static class PhoneNumber {
            public cat.ogasoft.examples.Example.Person.PhoneNumber dump(byte[] target) throws com.google.protobuf.InvalidProtocolBufferException {
                cat.ogasoft.protocolizer.messages.Example.Person.PhoneNumber result = cat.ogasoft.protocolizer.messages.Example.Person.PhoneNumber.parseFrom(target);
                return buildPhoneNumber(result);
            }

            public static cat.ogasoft.examples.Example.Person.PhoneNumber buildPhoneNumber(cat.ogasoft.protocolizer.messages.Example.Person.PhoneNumber target){
                cat.ogasoft.examples.Example.Person.PhoneNumber result = new cat.ogasoft.examples.Example.Person.PhoneNumber();
                result.setNumber(target.getNumber());
                if(target.hasType()){
                    result.setType(cat.ogasoft.examples.Example.Person.PhoneType.valueOf(target.getType().name()));
                }
                return result;
            }
        }
        public static cat.ogasoft.examples.Example.Person buildPerson(cat.ogasoft.protocolizer.messages.Example.Person target){
            cat.ogasoft.examples.Example.Person result = new cat.ogasoft.examples.Example.Person();
            result.setName(target.getName());
            result.setId(target.getId());
            if(target.hasEmail()){
                result.setEmail(target.getEmail());
            }
            if(target.getPhonesList() != null){
                java.util.ArrayList<cat.ogasoft.examples.Example.Person.PhoneNumber> r = new java.util.ArrayList<>(target.getPhonesCount());
                for(cat.ogasoft.protocolizer.messages.Example.Person.PhoneNumber k:target.getPhonesList()){
                    r.add(cat.ogasoft.protocolizer.dumppers.deserializers.ExampleDeserializer.Person.PhoneNumber.buildPhoneNumber(k));
                }
                result.setPhones(r);
            }
            return result;
        }
    }

    public static class AddressBook extends Thread implements cat.ogasoft.protocolizer.DeserializerProtoWorker<cat.ogasoft.examples.Example.AddressBook> {
        private byte[] bytes;
        private cat.ogasoft.examples.Example.AddressBook container;
        private String errMsg;


        @Override
        public void work(byte[] bytes) {
            super.setName("cat.ogasoft.protocolizer.dumppers.deserializers.ExampleDeserializer.AddressBookWorker");
            this.bytes = bytes;
            start();
        }

        @Override
        public cat.ogasoft.examples.Example.AddressBook waitUntilEnd() throws InterruptedException,cat.ogasoft.protocolizer.exceptions.DeserializationException {
            this.join();
            if(container == null){
                throw new cat.ogasoft.protocolizer.exceptions.DeserializationException("Deserialization exception, message [" + errMsg + "]");
            }
            return container;
        }

        @Override
        public void run() {
            try{
                container = dump(bytes);
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                errMsg = e.getMessage();
            }
        }
        public cat.ogasoft.examples.Example.AddressBook dump(byte[] target) throws com.google.protobuf.InvalidProtocolBufferException {
            cat.ogasoft.protocolizer.messages.Example.AddressBook result = cat.ogasoft.protocolizer.messages.Example.AddressBook.parseFrom(target);
            return buildAddressBook(result);
        }

        public static cat.ogasoft.examples.Example.AddressBook buildAddressBook(cat.ogasoft.protocolizer.messages.Example.AddressBook target){
            cat.ogasoft.examples.Example.AddressBook result = new cat.ogasoft.examples.Example.AddressBook();
            if(target.getPeopleList() != null){
                java.util.ArrayList<cat.ogasoft.examples.Example.Person> r = new java.util.ArrayList<>(target.getPeopleCount());
                for(cat.ogasoft.protocolizer.messages.Example.Person k:target.getPeopleList()){
                    r.add(cat.ogasoft.protocolizer.dumppers.deserializers.ExampleDeserializer.Person.buildPerson(k));
                }
                result.setPeople(r);
            }
            return result;
        }
    }

}
```

### Parse message.
Now, you only need write code for instantiate and fill your message. All the other are done.
```java
public class Parser {

    public static void main(String... args) throws Exception {
        Example.AddressBook message = generateMessage();
        byte[] raw = sender(message);
        System.out.println(Arrays.toString(raw));
        receiver(raw);
    }

    private static Example.AddressBook generateMessage() {
        List<Example.Person> people = new LinkedList<>();
        Example.Person peter = new Example.Person();
        peter.setId(1);
        peter.setName("peter");
        peter.setEmail("peter@protocolizer.com");
        List<Example.Person.PhoneNumber> peterPhones = new LinkedList<>();
        Example.Person.PhoneNumber pPeter = new Example.Person.PhoneNumber();
        pPeter.setNumber("647658711");
        pPeter.setType(Example.Person.PhoneType.MOBILE);
        peterPhones.add(pPeter);
        peter.setPhones(peterPhones);

        Example.Person mark = new Example.Person();
        mark.setId(2);
        mark.setName("mark");
        mark.setEmail("mark@protocolizer.com");
        List<Example.Person.PhoneNumber> markPhones = new LinkedList<>();
        Example.Person.PhoneNumber pMark = new Example.Person.PhoneNumber();
        pMark.setNumber("+34 911454571");
        pMark.setType(Example.Person.PhoneType.WORK);
        markPhones.add(pMark);
        mark.setPhones(markPhones);

        people.add(peter);
        people.add(mark);

        Example.AddressBook addr = new Example.AddressBook();
        addr.setPeople(people);
        return addr;
    }

    private static byte[] sender(Example.AddressBook addr) throws Exception {

        ExampleSerializer.AddressBook serializer = new ExampleSerializer.AddressBook();
        serializer.work(addr);
        return serializer.waitUntilEnd();
    }

    private static Example.AddressBook receiver(byte[] raw) throws Exception {
        ExampleDeserializer.AddressBook deserializer = new ExampleDeserializer.AddressBook();
        deserializer.work(raw);
        return deserializer.waitUntilEnd();
    }
}
```
In resume, you only need write two java classes (Example1.java & Parser.java in this example). 

-------------------------------------------------------------------
## Installation
You only need **four** very simple steps.
1. Download **Google Protocol Buffer** and configure it in your IDE.
2. Download **Protocolizer.jar** and add it at your class path.
3. Configure the annotation processor (**cat.ogasoft.protocolizer.processor.ProtoFileProcessorV2**) in your IDE or classpath.
4. Done.

-------------------------------------------------------------------
## Compile
To compile protoc files to Java messages you only need write a public abstract class annotated with **ProtoFileV2.Compiler** per project. The parameter *command* in this annotation must be the protoc (Protocol Buffer compiler) absolute path.

```java
@ProtoFileV2.Compiler(command = "/opt/protoc-3.2.0-linux-x86_64/bin/protoc")
public abstract class Compiler {

}
```