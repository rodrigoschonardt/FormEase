create table forms
(
    id bigint not null auto_increment,
    name varchar(100) not null,
    info text,
    ref_owner bigint not null,
    state varchar(50) not null,

    primary key(id),
    foreign key (ref_owner) references users(id)
);

create table fields
(
    id bigint not null auto_increment,
    label varchar(100) not null,
    required tinyint not null,
    ref_form bigint not null,
    type varchar(100) not null,

    primary key(id),
    foreign key (ref_form) references forms(id)
);