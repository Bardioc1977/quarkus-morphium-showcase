package io.quarkiverse.morphium.showcase.polymorphism;

import de.caluga.morphium.objectmapping.MorphiumTypeMapper;
import io.quarkiverse.morphium.showcase.polymorphism.entity.Money;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Map;

@ApplicationScoped
public class MoneyMapper implements MorphiumTypeMapper<Money> {

    @Override
    public Object marshall(Money o) {
        return Map.of(
                "amount", o.getAmount(),
                "currency", o.getCurrency()
        );
    }

    @Override
    public Money unmarshall(Object d) {
        if (d instanceof Map<?, ?> map) {
            double amount = ((Number) map.get("amount")).doubleValue();
            String currency = (String) map.get("currency");
            return new Money(amount, currency);
        }
        throw new IllegalArgumentException("Cannot unmarshall Money from: " + d);
    }
}
