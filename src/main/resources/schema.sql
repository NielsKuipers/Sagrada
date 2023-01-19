-- ObjectiveCard
create table IF NOT EXISTS objective_card
(
    id          serial primary key,
    name        varchar(100) not null,
    description varchar(255) not null,
    type        varchar(25)  not null,
    config      varchar(100) not null
);