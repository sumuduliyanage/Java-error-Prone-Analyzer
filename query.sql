SELECT 
    p.Id AS [PostId],
    p.PostTypeId, -- 1 for Questions, 2 for Answers
    p.CreationDate,
    p.Score,
    p.Body
FROM 
    Posts p
LEFT JOIN 
    Posts q ON p.ParentId = q.Id
WHERE 
    YEAR(p.CreationDate) = 2024
    AND MONTH(p.CreationDate) IN (6,7,8) -- Similarly, repeat for all the months as well
    AND (
        (p.PostTypeId = 1 AND p.Tags LIKE '%<java>%')
        OR (p.PostTypeId = 2 AND q.Tags LIKE '%<java>%')
    )
ORDER BY 
    p.CreationDate DESC;
