生成order_by_buy_amount.txt:
SELECT create_time, SUM(buy_amount) as buy_amount FROM crm_order GROUP BY create_time ASC;
生成order_by_pay_amount.txt:
SELECT create_time, SUM(pay_amount) as pay_amount FROM crm_order GROUP BY create_time ASC;
生成 RFM_data.txt
SELECT customer_id,MAX(create_time) as recent ,SUM(pay_amount) as monetary, COUNT(1) as frequency FROM crm_order WHERE customer_id != 0 GROUP BY customer_id Order BY customer_id;
都需要列标题，每行使用\t分隔