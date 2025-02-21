INSERT INTO shop_users (login, password, name, email, role)
VALUES  (
            'admin',
            '$2a$10$TZ/VOmJAMPbc3HREqW7uF.mC6DM9/FWIFNZKLHZjPE7brb1H077ie',
            'Admin',
            'admin@shop.de',
            'ROLE_ADMIN'
        ),
        (
            'john',
            '$2a$10$FhzgzuN/i46A3SC85E6O3OXQmpKFOnBR86QN9/A2RnRiugqs.3wke',
            'John',
            'john2.smith@example.com',
            'ROLE_CLIENT'
        ),
        (
            'jane',
            '$2a$10$llQ73O7CsfsqZa2REQWww.kMF04v5fZhgppeHw4ztSZGI1FSETOC.',
            'Jane',
            'jane2.smith@example.com',
            'ROLE_CLIENT'
        );
