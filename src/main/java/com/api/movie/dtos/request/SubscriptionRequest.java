package com.api.movie.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record SubscriptionRequest(
        @NotBlank(message = "Name is mandatory") String name,
        @NotNull(message = "Price is mandatory") @Positive(message = "Price must be greater than zero or positive") Double price,
        @NotNull(message = "Features are mandatory") @Size(min = 1, max = 10, message = "At least one feature is required") @NotNull(message = "At least one feature is required") String[] features,
        @NotNull(message = "Discount is mandatory") @PositiveOrZero(message = "Discount is mandatory") Double discount) {

}
