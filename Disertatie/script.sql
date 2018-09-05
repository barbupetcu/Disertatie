alter table dic_user_level rename column user_id to PERSO_USER_ID;
alter table ref_task_end add min_days number(3);
alter table ref_task_end add max_days number(3);