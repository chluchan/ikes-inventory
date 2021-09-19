create table "inventory_product" (
  id BIGSERIAL not null,
  name varchar(128) not null,
  description varchar(1024) not null,
  image_url varchar(512),
  primary key (id)
);

create table "inventory_stock" (
  id BIGSERIAL not null,
  product_id bigint not null,
  sales_status varchar(18) not null,
  rental_status varchar(18) not null,
  primary key (id),
  CONSTRAINT fk_inventory_product
      FOREIGN KEY(product_id)
	  REFERENCES inventory_product(id)
);
