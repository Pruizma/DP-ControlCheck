
    create table `accounting_record` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `creation` datetime(6),
        `status` integer,
        `title` varchar(255),
        `bookkeeper_id` integer not null,
        `investment_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `activity` (
       `id` integer not null,
        `version` integer not null,
        `budget_amount` double precision,
        `budget_currency` varchar(255),
        `end` datetime(6),
        `start` datetime(6),
        `title` varchar(255),
        `investment_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `administrator` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `anonymous` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `application` (
       `id` integer not null,
        `version` integer not null,
        `justification` varchar(255),
        `moment` datetime(6),
        `money_offer_amount` double precision,
        `money_offer_currency` varchar(255),
        `statement` varchar(255),
        `ticker` varchar(255),
        `investment_id` integer,
        `investor_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `authenticated` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `banner` (
       `id` integer not null,
        `version` integer not null,
        `name` varchar(255),
        `picture` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `bookkeeper` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `firm_name` varchar(255),
        `statement` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `bookkeeper_request` (
       `id` integer not null,
        `version` integer not null,
        `accepted` bit,
        `firm_name` varchar(255),
        `statement` varchar(255),
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `challenge` (
       `id` integer not null,
        `version` integer not null,
        `average_goal` varchar(255),
        `average_reward_amount` double precision,
        `average_reward_currency` varchar(255),
        `deadline` datetime(6),
        `description` varchar(255),
        `expert_goal` varchar(255),
        `expert_reward_amount` double precision,
        `expert_reward_currency` varchar(255),
        `rookie_goal` varchar(255),
        `rookie_reward_amount` double precision,
        `rookie_reward_currency` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `credit_card` (
       `id` integer not null,
        `version` integer not null,
        `brand` varchar(255),
        `credit_card_number` varchar(255),
        `cvv` varchar(255),
        `expiration_date` datetime(6),
        `holder` varchar(255),
        `banner_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `cuevas_bulletin` (
       `id` integer not null,
        `version` integer not null,
        `author` varchar(255),
        `moment` datetime(6),
        `text` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `customisation_parameter` (
       `id` integer not null,
        `version` integer not null,
        `activity_sectors` varchar(255),
        `spam_threshold` double precision,
        `spam_words` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `discussion_forum` (
       `id` integer not null,
        `version` integer not null,
        `aux` varchar(255),
        `title` varchar(255),
        `investment_id` integer,
        `leader_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `discussion_forum_authenticated` (
       `discussion_forum_id` integer not null,
        `accounts_id` integer not null
    ) engine=InnoDB;

    create table `entrepreneus` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `activity_sector` varchar(255),
        `qualification_record` varchar(255),
        `skill_record` varchar(255),
        `startup` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `fernandez_torre_bulletin` (
       `id` integer not null,
        `version` integer not null,
        `address` varchar(255),
        `author` varchar(255),
        `moment` datetime(6),
        primary key (`id`)
    ) engine=InnoDB;

    create table `garcia_roales_bulletin` (
       `id` integer not null,
        `version` integer not null,
        `birth` datetime(6),
        `name` varchar(255),
        `surname` varchar(255),
        `uvus` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `inquire` (
       `id` integer not null,
        `version` integer not null,
        `creation` datetime(6),
        `deadline` datetime(6),
        `description` varchar(255),
        `email` varchar(255),
        `money_max_amount` double precision,
        `money_max_currency` varchar(255),
        `money_min_amount` double precision,
        `money_min_currency` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `investment` (
       `id` integer not null,
        `version` integer not null,
        `description` varchar(255),
        `final_mode` bit not null,
        `moment` datetime(6),
        `money_amount` double precision,
        `money_currency` varchar(255),
        `quittel` varchar(255),
        `round` varchar(255),
        `ticker` varchar(255),
        `title` varchar(255),
        `url` varchar(255),
        `entrepreneus_id` integer not null,
        `investor_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `investor` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `activity_sector` varchar(255),
        `firm_name` varchar(255),
        `profile` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `message` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `creation_moment` datetime(6),
        `message_title` varchar(255),
        `sure` bit,
        `tags` varchar(255),
        `discussion_forum_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `notice` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `deadline` datetime(6),
        `header_image` varchar(255),
        `link` varchar(255),
        `moment` datetime(6),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `offer` (
       `id` integer not null,
        `version` integer not null,
        `aux` bit,
        `link` varchar(255),
        `pass` varchar(255),
        `pass_prot` bit,
        `title` varchar(255),
        `application_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `overture` (
       `id` integer not null,
        `version` integer not null,
        `deadline` datetime(6),
        `description` varchar(255),
        `email` varchar(255),
        `max_money_amount` double precision,
        `max_money_currency` varchar(255),
        `min_money_amount` double precision,
        `min_money_currency` varchar(255),
        `moment` datetime(6),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `pardo_lopez_bulletin` (
       `id` integer not null,
        `version` integer not null,
        `content` varchar(255),
        `moment` datetime(6),
        `name` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `portero_montano_bulletin` (
       `id` integer not null,
        `version` integer not null,
        `film` varchar(255),
        `moment` datetime(6),
        `review` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `ruiz_mateos_bulletin` (
       `id` integer not null,
        `version` integer not null,
        `description` varchar(255),
        `moment` datetime(6),
        `name` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `technology_records` (
       `id` integer not null,
        `version` integer not null,
        `activity_sector` varchar(255),
        `description` varchar(255),
        `email` varchar(255),
        `inventor` varchar(255),
        `open_source` bit,
        `stars` integer,
        `title` varchar(255),
        `website` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `tool_record` (
       `id` integer not null,
        `version` integer not null,
        `activity_sector` varchar(255),
        `description` varchar(255),
        `email` varchar(255),
        `inventor_name` varchar(255),
        `open_source` bit not null,
        `stars` integer,
        `title` varchar(255),
        `web_site` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `user_account` (
       `id` integer not null,
        `version` integer not null,
        `enabled` bit not null,
        `identity_email` varchar(255),
        `identity_name` varchar(255),
        `identity_surname` varchar(255),
        `password` varchar(255),
        `username` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `hibernate_sequence` (
       `next_val` bigint
    ) engine=InnoDB;

    insert into `hibernate_sequence` values ( 1 );

    alter table `application` 
       add constraint UK_ao7wxw7e7mkj6g5q49yq2fw8d unique (`ticker`);

    alter table `discussion_forum` 
       add constraint UK_dvlm5hspa5xci3tdbet96snw0 unique (`leader_id`);

    alter table `investment` 
       add constraint UK_i5ustbh87xrpsxd0lql1e51pg unique (`ticker`);

    alter table `offer` 
       add constraint UK_f59xuo7xtxx3go73x8amehj0f unique (`application_id`);

    alter table `user_account` 
       add constraint UK_castjbvpeeus0r8lbpehiu0e4 unique (`username`);

    alter table `accounting_record` 
       add constraint `FK41jm4vk7runvmg5tderffrele` 
       foreign key (`bookkeeper_id`) 
       references `bookkeeper` (`id`);

    alter table `accounting_record` 
       add constraint `FKcggg8hcmhohhlaeka6ov3thfh` 
       foreign key (`investment_id`) 
       references `investment` (`id`);

    alter table `activity` 
       add constraint `FK8cnxt6m91noqrbuk63luhvebv` 
       foreign key (`investment_id`) 
       references `investment` (`id`);

    alter table `administrator` 
       add constraint FK_2a5vcjo3stlfcwadosjfq49l1 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `anonymous` 
       add constraint FK_6lnbc6fo3om54vugoh8icg78m 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `application` 
       add constraint `FKsvojjwodcrk0011w2tv8qpp9w` 
       foreign key (`investment_id`) 
       references `investment` (`id`);

    alter table `application` 
       add constraint `FKl4fp0cd8c008ma79n6w58xhk9` 
       foreign key (`investor_id`) 
       references `investor` (`id`);

    alter table `authenticated` 
       add constraint FK_h52w0f3wjoi68b63wv9vwon57 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `bookkeeper` 
       add constraint FK_krvjp9eaqyapewl2igugbo9o8 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `bookkeeper_request` 
       add constraint `FKrkmyfaktfktoo2v26a9qu4ebb` 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `credit_card` 
       add constraint `FKa4pbn9v8sv66p46fsrke8ow89` 
       foreign key (`banner_id`) 
       references `banner` (`id`);

    alter table `discussion_forum` 
       add constraint `FKrclodsb01yx8qf4lkee4d3oas` 
       foreign key (`investment_id`) 
       references `investment` (`id`);

    alter table `discussion_forum` 
       add constraint `FKo46t7suh5hrq0eqy1ogf7ctow` 
       foreign key (`leader_id`) 
       references `authenticated` (`id`);

    alter table `discussion_forum_authenticated` 
       add constraint `FKcye1tya942juu888lgqjo2lt6` 
       foreign key (`accounts_id`) 
       references `authenticated` (`id`);

    alter table `discussion_forum_authenticated` 
       add constraint `FKo0i6i5a8flkqrnjuyxvxqt7ms` 
       foreign key (`discussion_forum_id`) 
       references `discussion_forum` (`id`);

    alter table `entrepreneus` 
       add constraint FK_smdr3gcq5hfemr5haomwsirbr 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `investment` 
       add constraint `FKou4vblbxtiq3hgqc20p93bd3r` 
       foreign key (`entrepreneus_id`) 
       references `entrepreneus` (`id`);

    alter table `investment` 
       add constraint `FKj6g1c0ihudgdjdeskjw8ly4x` 
       foreign key (`investor_id`) 
       references `investor` (`id`);

    alter table `investor` 
       add constraint FK_dcek5rr514s3rww0yy57vvnpq 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `message` 
       add constraint `FKr2om5f6tefk2fg0fyl53q2kgd` 
       foreign key (`discussion_forum_id`) 
       references `discussion_forum` (`id`);

    alter table `offer` 
       add constraint `FKsw0w3bk6u9fby3rbg0fsf2ija` 
       foreign key (`application_id`) 
       references `application` (`id`);
