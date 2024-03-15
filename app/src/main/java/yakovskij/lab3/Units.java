package yakovskij.lab3;

import androidx.annotation.NonNull;

public class Units {
    public String name;
    public double coeff;

    public Units(String name, double coeff){
        this.name = name;
        this.coeff = coeff;
    }

    @NonNull
    public String toString() {
        return name;
    }
}
