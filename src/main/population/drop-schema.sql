
    alter table `accounting_record` 
       drop 
       foreign key `FK41jm4vk7runvmg5tderffrele`;

    alter table `accounting_record` 
       drop 
       foreign key `FKcggg8hcmhohhlaeka6ov3thfh`;

    alter table `activity` 
       drop 
       foreign key `FK8cnxt6m91noqrbuk63luhvebv`;

    alter table `administrator` 
       drop 
       foreign key FK_2a5vcjo3stlfcwadosjfq49l1;

    alter table `anonymous` 
       drop 
       foreign key FK_6lnbc6fo3om54vugoh8icg78m;

    alter table `application` 
       drop 
       foreign key `FKsvojjwodcrk0011w2tv8qpp9w`;

    alter table `application` 
       drop 
       foreign key `FKl4fp0cd8c008ma79n6w58xhk9`;

    alter table `authenticated` 
       drop 
       foreign key FK_h52w0f3wjoi68b63wv9vwon57;

    alter table `bookkeeper` 
       drop 
       foreign key FK_krvjp9eaqyapewl2igugbo9o8;

    alter table `bookkeeper_request` 
       drop 
       foreign key `FKrkmyfaktfktoo2v26a9qu4ebb`;

    alter table `credit_card` 
       drop 
       foreign key `FKa4pbn9v8sv66p46fsrke8ow89`;

    alter table `discussion_forum` 
       drop 
       foreign key `FKrclodsb01yx8qf4lkee4d3oas`;

    alter table `discussion_forum` 
       drop 
       foreign key `FKo46t7suh5hrq0eqy1ogf7ctow`;

    alter table `discussion_forum_authenticated` 
       drop 
       foreign key `FKcye1tya942juu888lgqjo2lt6`;

    alter table `discussion_forum_authenticated` 
       drop 
       foreign key `FKo0i6i5a8flkqrnjuyxvxqt7ms`;

    alter table `entrepreneus` 
       drop 
       foreign key FK_smdr3gcq5hfemr5haomwsirbr;

    alter table `investment` 
       drop 
       foreign key `FKou4vblbxtiq3hgqc20p93bd3r`;

    alter table `investment` 
       drop 
       foreign key `FKj6g1c0ihudgdjdeskjw8ly4x`;

    alter table `investor` 
       drop 
       foreign key FK_dcek5rr514s3rww0yy57vvnpq;

    alter table `message` 
       drop 
       foreign key `FKr2om5f6tefk2fg0fyl53q2kgd`;

    alter table `offer` 
       drop 
       foreign key `FKsw0w3bk6u9fby3rbg0fsf2ija`;

    drop table if exists `accounting_record`;

    drop table if exists `activity`;

    drop table if exists `administrator`;

    drop table if exists `anonymous`;

    drop table if exists `application`;

    drop table if exists `authenticated`;

    drop table if exists `banner`;

    drop table if exists `bookkeeper`;

    drop table if exists `bookkeeper_request`;

    drop table if exists `challenge`;

    drop table if exists `credit_card`;

    drop table if exists `cuevas_bulletin`;

    drop table if exists `customisation_parameter`;

    drop table if exists `discussion_forum`;

    drop table if exists `discussion_forum_authenticated`;

    drop table if exists `entrepreneus`;

    drop table if exists `fernandez_torre_bulletin`;

    drop table if exists `garcia_roales_bulletin`;

    drop table if exists `inquire`;

    drop table if exists `investment`;

    drop table if exists `investor`;

    drop table if exists `message`;

    drop table if exists `notice`;

    drop table if exists `offer`;

    drop table if exists `overture`;

    drop table if exists `pardo_lopez_bulletin`;

    drop table if exists `portero_montano_bulletin`;

    drop table if exists `ruiz_mateos_bulletin`;

    drop table if exists `technology_records`;

    drop table if exists `tool_record`;

    drop table if exists `user_account`;

    drop table if exists `hibernate_sequence`;
