package com.hendisantika.dynamicmultitenancy.tenant.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-security-jwt-rest-api-dynamic-multi-tenancy-mysql-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/05/20
 * Time: 05.58
 */
@Entity
@Table(name = "tbl_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @Size(max = 50)
    @Column(name = "product_name", nullable = false)
    private String productName;

    @Size(max = 10)
    @Column(name = "quantity", nullable = false)
    private String quantity;

    @Size(max = 3)
    @Column(name = "size", nullable = false, unique = true)
    private String size;

    public Product(@Size(max = 50) String productName, @Size(max = 10) String quantity, @Size(max = 3) String size) {
        this.productName = productName;
        this.quantity = quantity;
        this.size = size;
    }
}
