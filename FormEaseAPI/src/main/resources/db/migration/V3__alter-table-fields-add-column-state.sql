alter table fields add column state varchar(50);
update fields set state = 'ACTIVE';