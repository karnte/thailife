--1
SELECT *
FROM partnerbenefit.benefit_master
WHERE partner_code = '071.xxx';
--2
SELECT *
FROM partnerbenefit.benefit_master
WHERE policy_status_date BETWEEN '2020-01-01' AND '2020-12-31';
--3
SELECT *
FROM partnerbenefit.benefit_master
WHERE sum_insured BETWEEN 100000 AND 500000;

