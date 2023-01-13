select
    tp_json::jsonb as tp_json,
    tp_id::uuid as tp_id,
    portals_published
from
    public.theme_publication
;