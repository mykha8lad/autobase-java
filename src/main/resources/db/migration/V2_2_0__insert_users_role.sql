INSERT INTO user_role (user_id, role_id)
SELECT id, 2
FROM driver
WHERE id BETWEEN 1081 AND 1100
ON CONFLICT DO NOTHING;