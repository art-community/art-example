package ru.model;

import lombok.*;

@Value
@AllArgsConstructor
@Builder(toBuilder = true)
public class Model {
    String value;
}
