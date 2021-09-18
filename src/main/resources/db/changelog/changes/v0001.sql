create table "inventory_product" (
  id bigint not null,
  name varchar(128) not null,
  description varchar(1024) not null,
  image_url varchar(512) not null,
  primary key (id)
);
