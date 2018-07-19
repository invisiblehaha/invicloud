生成order_by_buy_amount.txt:
SELECT create_time, SUM(buy_amount) as buy_amount FROM crm_order GROUP BY create_time ASC;
生成order_by_pay_amount.txt:
SELECT create_time, SUM(pay_amount) as pay_amount FROM crm_order GROUP BY create_time ASC;
生成 RFM_data.txt
SELECT customer_id,MAX(create_time) as recent ,SUM(pay_amount) as monetary, COUNT(1) as frequency FROM crm_order WHERE customer_id != 0 GROUP BY customer_id Order BY customer_id;
都需要列标题，每行使用\t分隔

生成 vip_increase.txt:
select substr(regeist_time,1,10) as rgt_time,
count(*) as rgt_amount
from crm_customer
group by substr(regeist_time,1,10)
ORDER BY rgt_time;

生成category_recommendation_data.txt:
select customer_id,
category_id
FROM crm_order_product op,crm_order o, crm_product_category pc
where op.order_id=o.order_id and op.product_id=pc.product_id and customer_id>0

生成 predict_single_product_data.txt
每次需要不同的 p.product_id=？
列表的日期数据需要是连续的，若有日期缺失，需要补充
SELECT  o.create_time, p.buy_amount FROM crm_order o, crm_order_product p WHERE o.order_id=p.order_id and p.product_id=0 ORDER BY p.product_id,o.create_time;

select pc.category_id,SUM(o.buy_amount) as buy_total
from crm_order o, crm_order_product op,crm_product_category pc
where o.customer_id=0 and o.order_id=op.order_id and op.product_id=pc.product_id
GROUP BY pc.category_id ORDER BY pc.category_id;