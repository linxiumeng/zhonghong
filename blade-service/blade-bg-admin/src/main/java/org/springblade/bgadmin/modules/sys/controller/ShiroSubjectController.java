package org.springblade.bgadmin.modules.sys.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hanbin
 */
@RestController
@RequestMapping("/api/permission")
public class ShiroSubjectController {

    /*@Resource
    ShiroTag shiroTag;


    @RequestMapping("/hasPermission")
    public R hasPermission(String permission) {
        permission = "sys:role:save";
        boolean isPermitted = shiroTag.hasPermission(permission);
        return R.ok().put("permitted", isPermitted);
    }


    @RequestMapping("/list")
    public R getPermissionList() {
        *//*Map<String, List<String>> resultMap = new HashMap<>(32);
        AuthorizationInfo authorizationInfo = shiroTag.getAllPermission();
        Collection<String> permissionList = authorizationInfo.getStringPermissions();
        for (String permission : permissionList) {

            String[] arr = permission.split(":");
            String mapKey = arr[0] + ":" + arr[1];

            resultMap.compute(mapKey, (k, v) -> {
                        if (v == null) {
                            v = new LinkedList<>();
                        }
                        v.add(permission);
                        return v;
                    }
            );

        }*//*


        return R.ok();//.put("result", resultMap);
    }
*/

}
