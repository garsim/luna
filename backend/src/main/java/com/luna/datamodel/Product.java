package com.luna.datamodel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
//import com.fasterxml.jackson.annotation.JsonRootName;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    private final Integer id;
    private final String ean;
    private final Double weight;
    private final BigDecimal price;
    private final String name;
    private final String description;
    private final Boolean assembled;
    private final Dimension dimension;
    private final String color;

    @JsonCreator
    public Product(@JsonProperty("id") Integer id,
                   @JsonProperty("ean") String ean,
                   @JsonProperty("weight") Double weight,
                   @JsonProperty("price") BigDecimal price,
                   @JsonProperty("name") String name,
                   @JsonProperty("description") String description,
                   @JsonProperty("assembled") Boolean assembled,
                   @JsonProperty("dimension") Dimension dimension,
                   @JsonProperty("color") String color) {
        this.id = id;
        this.ean = ean;
        this.weight = weight;
        this.price = price;
        this.name = name;
        this.description = description;
        this.assembled = assembled;
        this.dimension = dimension;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public String getEan() {
        return ean;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Boolean isAssembled() {
        return assembled!=null && assembled;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public String getColor() {
        return color;
    }

    public Double getWeight(){
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return new EqualsBuilder().append(id, product.id).append(ean, product.ean).append(weight, product.weight).append(price, product.price).append(name, product.name).append(description, product.description).append(assembled, product.assembled).append(dimension, product.dimension).append(color, product.color).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(ean).append(weight).append(price).append(name).append(description).append(assembled).append(dimension).append(color).toHashCode();
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", ean='" + ean + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", assembled=" + assembled +
                ", dimension=" + dimension +
                ", color='" + color + '\'' +
                '}';
    }

    static class Dimension {
        private final Double depth;
        private final Double width;
        private final Double height;

        @JsonCreator
        public Dimension(@JsonProperty("depth") Double depth,
                         @JsonProperty("width") Double width,
                         @JsonProperty("height") Double height) {
            this.depth = depth;
            this.width = width;
            this.height = height;
        }

        public Double getDepth() {
            return depth;
        }

        public Double getWidth() {
            return width;
        }

        public Double getHeight() {
            return height;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            Dimension dimension = (Dimension) o;

            return new EqualsBuilder().append(depth, dimension.depth).append(width, dimension.width).append(height, dimension.height).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(depth).append(width).append(height).toHashCode();
        }

        @Override
        public String toString() {
            return "Dimension{" +
                    "depth=" + depth +
                    ", width=" + width +
                    ", height=" + height +
                    '}';
        }
    }


}
