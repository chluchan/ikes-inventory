create table "inventory_product" (
  id bigint not null,
  name varchar(128) not null,
  description varchar(1024) not null,
  image_url varchar(512),
  primary key (id)
);

CREATE TYPE stock_sales_status AS ENUM ('AVAILABLE_FOR_SALE', 'NOT_FOR_SALE', 'SOLD');
CREATE TYPE stock_rental_status AS ENUM ('AVAILABLE_FOR_RENT', 'NOT_FOR_RENT');

create table "inventory_stock" (
  id bigint not null,
  product_id bigint not null,
  sales_status stock_sales_status not null,
  rental_status stock_rental_status not null,
  primary key (id),
  CONSTRAINT fk_inventory_product
      FOREIGN KEY(product_id)
	  REFERENCES inventory_product(id)
);
