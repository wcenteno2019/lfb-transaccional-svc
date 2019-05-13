package com.loyalty.lfbtransaccionalsvc.pojo.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "tipo"
})
public class Cuenta {
        @JsonProperty("id")
        private String id;
        @JsonProperty("name")
        private String name;
        @JsonProperty("tipo")
        private int tipo;

        /**
         * No args constructor for use in serialization
         *
         */
        public Cuenta() {
        }

        /**
         *
         * @param id
         * @param tipo
         * @param name
         */
        public Cuenta(String id, String name, int tipo) {
            super();
            this.id = id;
            this.name = name;
            this.tipo = tipo;
        }

        @JsonProperty("id")
        public String getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(String id) {
            this.id = id;
        }

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        @JsonProperty("name")
        public void setName(String name) {
            this.name = name;
        }

        @JsonProperty("tipo")
        public int getTipo() {
            return tipo;
        }

        @JsonProperty("tipo")
        public void setTipo(int tipo) {
            this.tipo = tipo;
        }

    }
