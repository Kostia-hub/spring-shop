drop table if exists products cascade;
create table products (id bigserial, title varchar(255), description varchar(5000), price numeric(8, 2), primary key(id));
insert into products
(title, description, price) values
('Cheese', 'Fresh cheese', 320.0),
('Milk', 'Fresh milk', 80.0),
('Apples', 'Fresh apples', 80.0),
('Bread', 'Fresh bread', 30.0);

drop table if exists categories cascade;
create table categories (id bigserial, title varchar(255), primary key(id));
insert into categories
(title) values
('Food'),
('Devices');

drop table if exists products_categories cascade;
create table products_categories (product_id bigint not null, category_id bigint not null, primary key(product_id, category_id),
foreign key (product_id) references products(id), foreign key (category_id) references categories(id));
insert into products_categories (product_id, category_id) values (1, 1), (2, 1), (3, 1), (4, 2);

drop table if exists users;
create table users (
  id                    bigserial,
  phone                 VARCHAR(30) not null UNIQUE,
  password              VARCHAR(80) not null,
  email                 VARCHAR(50) UNIQUE,
  first_name            VARCHAR(50),
  last_name             VARCHAR(50),
  PRIMARY KEY (id)
);

drop table if exists roles;
create table roles (
  id                    serial,
  name                  VARCHAR(50) not null,
  primary key (id)
);

drop table if exists users_roles;
create table users_roles (
  user_id               INT NOT NULL,
  role_id               INT NOT NULL,
  primary key (user_id, role_id),
  FOREIGN KEY (user_id)
  REFERENCES users (id),
  FOREIGN KEY (role_id)
  REFERENCES roles (id)
);

insert into roles (name)
values
('ROLE_CUSTOMER'), ('ROLE_MANAGER'), ('ROLE_ADMIN');

insert into users (phone, password, first_name, last_name, email)
values
('admin','$2y$12$rxg3cYnpaZsNwAVuaiKZXeDb69dR.h9foNdfARmOp/9UBRLymKV22','admin','admin','admin@gmail.com');

insert into users_roles (user_id, role_id)
values
(1, 1),
(1, 2),
(1, 3);

drop table if exists orders cascade;
create table orders (id bigserial, user_id bigint not null, price numeric(8, 2) not null, status varchar(32), address varchar (255) not null, phone_number varchar(30) not null, primary key(id), constraint fk_user_id foreign key (user_id) references users (id));

drop table if exists items cascade;
create table items (id bigserial, product_id bigint not null, quantity int, price numeric(8, 2), primary key(id), constraint fk_prod_id foreign key (product_id) references products (id));

drop table if exists orders_items cascade;
create table order_items(id bigserial, order_id bigint not null, item_id bigint not null, primary key(id), constraint fk_order_items_id foreign key(order_id) references orders(id), constraint fk_item_id foreign key (item_id) references items(id));

alter table users add column age integer;
