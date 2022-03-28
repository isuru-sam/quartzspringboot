create table question (
  id bigserial not null,
  exam_id bigint not null references exam (id),
  question_order bigint not null,
  description text not null,
  primary key (id)
);