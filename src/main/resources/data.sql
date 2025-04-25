CREATE TABLE IF NOT EXISTS roles(
    role INT PRIMARY KEY, 
    role_name VARCHAR(25) NOT NULL 
);

INSERT INTO  roles (id, role_name) VALUES (1, 'ADMIN')
ON CONFLICT (id) DO NOTHING; 
INSERT INTO  roles (id, role_name) VALUES (2, 'MANAGER')
ON CONFLICT (id) DO NOTHING; 
INSERT INTO  roles (id, role_name) VALUES (3, 'USER')
ON CONFLICT (id) DO NOTHING; 
INSERT INTO  roles (id, role_name) VALUES (4, 'VISITOR')
ON CONFLICT (id) DO NOTHING; 

