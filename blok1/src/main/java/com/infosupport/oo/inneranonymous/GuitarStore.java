package com.infosupport.oo.inneranonymous;

import java.util.List;

public class GuitarStore {
    private List<Guitar> guitars;

    public GuitarStore(List<Guitar> guitars) {
        this.guitars = guitars;

        GuitarType type = GuitarType.TELECASTER;
    }

    public void addGuitar(Object guitar) {
        var isGuitarCondition = new Condition() {
            @Override
            public boolean evaluate(Object input) {
                return input instanceof Guitar;
            }
        };
        Condition anotherGuitarCondition = input -> input instanceof Guitar;

        if (anotherGuitarCondition.evaluate(guitar)) {
            guitars.add((Guitar) guitar);
        }
    }

    class ShippingInformation {
        int shippingCostPerGuitar;

        ShippingInformation(int shippingCostPerGuitar) {
            this.shippingCostPerGuitar = shippingCostPerGuitar;
        }

        int calculateShippingCosts() {
            return guitars.size() * shippingCostPerGuitar;
        }
    }
}
