create table exam (
  id bigserial not null,
  title varchar(50) not null,
  description varchar(512) not null,
  primary key (id)
);