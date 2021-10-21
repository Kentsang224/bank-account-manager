DROP TABLE IF EXISTS bank_account;

create table bank_account (
    account_number varchar(255) not null,
    currency varchar(255) not null,
    status varchar(255) not null,
    balance decimal(19,2) not null,
    created_time timestamp not null,
    updated_time timestamp,
    primary key (account_number)
)
